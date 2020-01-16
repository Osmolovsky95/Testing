package dao;


import data.question.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO implements DAO {

    public static void addAnswer(Question question,int trueNumber)  {
        String insertSQL = "INSERT INTO answers (answer) Values (?) RETURNING id";
       try {
           PreparedStatement preparedStatement = new AnswerDAO().getPreparedStatement(insertSQL);
           List<Integer> idAnswers = new ArrayList<>();
           for (String answer : question.getAnswers()) {
               preparedStatement.setString(1, answer);
               ResultSet rs = preparedStatement.executeQuery();
               int id = 0;
               while (rs.next()) {
                   id = rs.getInt(1);
                   idAnswers.add(id);
               }
           }
           question.setIdAnswers(idAnswers);
           question.setTrueNumber(question.getIdAnswers().get(trueNumber-1));
           AnswerDAO.addTrueAnswer((question.getIdAnswers().get(trueNumber-1)),question.getId());
           preparedStatement.getConnection().close();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    public static void addTrueAnswer(long answer_id,long question_id)  {
        String insertSQL = "INSERT INTO trueAnswers (id_answer,id_question) Values (?,?)";
        try{
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertSQL) ;
        preparedStatement.setLong(1, answer_id);
        preparedStatement.setLong(2, question_id);
        preparedStatement.execute();
        preparedStatement.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
