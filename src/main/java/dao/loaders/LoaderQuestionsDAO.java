package dao.loaders;

import dao.DAO;
import question.Question;
import question.BankQuestions;
import java.sql.*;



public class LoaderQuestionsDAO implements ILoader, DAO {


    public void load()  {
        try {
            String sql="SELECT * FROM questions";
            PreparedStatement preparedStatement = this.getPreparedStatement(sql);
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
              PreparedStatement preparedStatement1=this.getPreparedStatement(sqlTrueAnswer);
              preparedStatement1.setLong(1,question.getId());
              ResultSet rs=preparedStatement1.executeQuery();
              while (rs.next()){
                  long idTrueAnswer= rs.getInt("id_answer");
                  question.setTrueNumber(idTrueAnswer);
              }

              String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
              PreparedStatement preparedStatement2 = this.getPreparedStatement(sqlAnswers);
              preparedStatement2.setLong(1, question.getId());
              ResultSet answers= preparedStatement2.executeQuery();
              String sqlAnswerText="SELECT * FROM answers WHERE id=?";
              PreparedStatement preparedStatement3=this.getPreparedStatement(sqlAnswerText);
             while(answers.next()){
                int idAnswer = Integer.parseInt(answers.getString("answer_id"));
                question.getIdAnswers().add(idAnswer);
                preparedStatement3.setLong(1,idAnswer);
               ResultSet answerText = preparedStatement3.executeQuery();
               while(answerText.next()){
                  String answerContent= answerText.getString("answer");
                  question.getAnswers().add(answerContent);
               }
             }
             preparedStatement3.getConnection().close();
             preparedStatement3.getConnection().close();
             preparedStatement3.getConnection().close();
              BankQuestions.getInstance().getQuestions().add(question);
          }
          preparedStatement.getConnection().close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
