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
           switch (trueNumber) {
               case 1:
                   question.setTrueNumber(question.getIdAnswers().get(0));
                   AnswerDAO.addTrueAnswer((question.getIdAnswers().get(0)),question.getId());
                   break;
               case 2:
                   question.setTrueNumber(question.getIdAnswers().get(1));
                   AnswerDAO.addTrueAnswer((question.getIdAnswers().get(1)),question.getId());
                   break;
               case 3:
                   question.setTrueNumber(question.getIdAnswers().get(2));
                   AnswerDAO.addTrueAnswer((question.getIdAnswers().get(2)),question.getId());
                   break;
               case 4:
                   question.setTrueNumber(question.getIdAnswers().get(3));
                   AnswerDAO.addTrueAnswer((question.getIdAnswers().get(3)),question.getId());
                   break;
           }
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
