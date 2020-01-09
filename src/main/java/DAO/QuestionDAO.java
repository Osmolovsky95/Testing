package DAO;


import question.Question;
import java.sql.*;
import java.util.List;

public class QuestionDAO implements DAO {

    public static Question addQuestion(String question,double assessment) throws SQLException, ClassNotFoundException {
        String insertQuestion="INSERT INTO questions (question,assessment) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement = new QuestionDAO().getPreparedStatement(insertQuestion);
        preparedStatement.setString(1, question);
        preparedStatement.setDouble(2, assessment);

        ResultSet rs= preparedStatement.executeQuery();
        long id=0;
        while (rs.next()){
            id= rs.getInt(1);
        }
        //Добавляем ответы
        preparedStatement.getConnection().close();
        Question question1=new Question(question,id);
        question1.setAssessment(assessment);
        return question1;
    }

    public static void addQuestionAnswers(long question_id,long answer_id) throws SQLException, ClassNotFoundException {
        String insertQuestion="INSERT INTO question_answers  (question_id,answer_id) Values (?,?)";
        PreparedStatement preparedStatement = new QuestionDAO().getPreparedStatement(insertQuestion);
        preparedStatement.setLong(1,question_id );
        preparedStatement.setLong(2, answer_id);
        preparedStatement.execute();
        preparedStatement.getConnection().close();
    }


    public static ResultSet getQuestions() throws SQLException, ClassNotFoundException {
        String insertQuestion="SELECT * FROM questions";
        PreparedStatement preparedStatement = new QuestionDAO().getPreparedStatement(insertQuestion);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet;
    }

    public static void addAnswersToQuestion(Question question,List<String> answers){
        for (String answer: answers){
            question.getAnswers().add(answer);
        }
    }
}
