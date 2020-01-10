package loaders;

import question.Question;
import question.BankQuestions;
import java.sql.*;



public class LoaderQuestions implements ILoader {
   private String url = "jdbc:postgresql://localhost:5432/Testing";
   private String name = "postgres";
   private String password = "postgres";

    public void load()  {
        try {
            String sql="SELECT * FROM questions";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet questions = preparedStatement.executeQuery();

          while (questions.next()) {
              Question question=new Question();
              long id = Long.parseLong(questions.getString("id"));
              String questionText = questions.getString("question");
              double assessment = Double.parseDouble(questions.getString("assessment"));
              question.setAssessment(assessment);
              question.setQuestion(questionText);
              question.setId(id);

              String sqlTrueAnswer="SELECT id_answer FROM trueAnswers WHERE id_question=?";
              PreparedStatement preparedStatement3=connection.prepareStatement(sqlTrueAnswer);
              preparedStatement3.setLong(1,question.getId());
              ResultSet rs=preparedStatement3.executeQuery();
              while (rs.next()){
                  long idTrueAnswer= rs.getInt("id_answer");
                  question.setTrueNumber(idTrueAnswer);
              }

              String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
              PreparedStatement preparedStatement1 = connection.prepareStatement(sqlAnswers);
              preparedStatement1.setLong(1, question.getId());
              ResultSet answers= preparedStatement1.executeQuery();
              String sqlAnswerText="SELECT * FROM answers WHERE id=?";
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
             }
              BankQuestions.getInstance().getQuestions().add(question);
          }
          connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
