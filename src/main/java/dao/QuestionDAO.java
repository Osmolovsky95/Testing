package dao;


import data.question.BankQuestions;
import data.question.Question;
import java.sql.*;
import java.util.List;

public class QuestionDAO implements DAO {

    public static void addQuestion(Question question)  {
        String insertQuestion="INSERT INTO questions (question,assessment) Values (?,?) RETURNING id";
      try {
          PreparedStatement preparedStatement = new QuestionDAO().getPreparedStatement(insertQuestion);
          preparedStatement.setString(1, question.getQuestion());
          preparedStatement.setDouble(2, question.getAssessment());
          ResultSet rs = preparedStatement.executeQuery();
          long id = 0;
          while (rs.next()) {
              id = rs.getInt(1);
          }
          //Добавляем ответы
          question.setId(id);
          preparedStatement.getConnection().close();
      } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
      }
        BankQuestions.getInstance().getQuestions().add(question);
    }

    public static void addQuestionAnswers(long question_id,long answer_id)  {
        String insertQuestion="INSERT INTO question_answers  (question_id,answer_id) Values (?,?)";
      try {
          PreparedStatement preparedStatement = new QuestionDAO().getPreparedStatement(insertQuestion);
          preparedStatement.setLong(1, question_id);
          preparedStatement.setLong(2, answer_id);
          preparedStatement.execute();
          preparedStatement.getConnection().close();
      } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
      }
    }

    public static void addAnswersToQuestion(Question question,List<String> answers){
        for (String answer: answers){
            question.getAnswers().add(answer);
        }
    }
}
