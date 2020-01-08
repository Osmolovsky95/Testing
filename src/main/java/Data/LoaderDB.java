package Data;

import Question.Question;
import servlets.ListServlet;
import Question.BankQuestions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaderDB {
    String url = "jdbc:postgresql://localhost:5432/Testing";
    String name = "postgres";
    String password = "postgres";

    // TODO: 08.01.2020 неправильно
    public void createQuestionsFromDB()  {
        try {
            String sql="select * from questions";
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(url, name, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet questions = preparedStatement.executeQuery();

          Question question=new Question();
          while (questions.next()) {
              long id = Long.parseLong(questions.getString("id"));
              String questionText = questions.getString("question");
              double assessment = Double.parseDouble(questions.getString("assessment"));
              question.setAssessment(assessment);
              question.setQuestion(questionText);
              question.setId(id);

              String sqlAnswers = "select * from question_answers where question_id=?";
              PreparedStatement preparedStatement1 = connection.prepareStatement(sqlAnswers);
              preparedStatement1.setLong(1, question.getId());
              ResultSet answers= preparedStatement1.executeQuery();
              String sqlAnswerText="select * from answers where id=?";
              PreparedStatement preparedStatement2=connection.prepareStatement(sqlAnswerText);
             while(answers.next()){
                long idAnswer = Long.parseLong(answers.getString("answer_id"));
                question.getIdAnswers().add(idAnswer);
                preparedStatement2.setLong(1,idAnswer);
               ResultSet answerText = preparedStatement2.executeQuery();
               while(answerText.next()){
                  String answerContent= answerText.getString("answer");
                  question.getAnswers().add(answerContent);
               }
               String sqlTrueAnswer="select id_answer from trueAnswers where id_question=?";
               PreparedStatement preparedStatement3=connection.prepareStatement(sqlTrueAnswer);
               preparedStatement3.setLong(1,question.getId());
               ResultSet rs=preparedStatement3.executeQuery();
               while (rs.next()){
                long idTrueAnswer= rs.getInt("id_answer");
                question.setTrueNumber(idTrueAnswer);
               }

             }
              System.out.println(question);
              BankQuestions.getInstance().getQuestions().add(question);
          }
          connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }
}
