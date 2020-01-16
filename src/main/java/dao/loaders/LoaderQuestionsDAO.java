package dao.loaders;

import dao.DAO;
import data.question.Question;
import data.question.BankQuestions;
import java.sql.*;



public class LoaderQuestionsDAO implements ILoader, DAO {

// TODO: 16.01.2020

    public void load() {
        try {
            String sql = "SELECT * FROM questions";
            PreparedStatement preparedStatement = this.getPreparedStatement(sql);
            ResultSet questions = preparedStatement.executeQuery();

            while (questions.next()) {
                Question question = new Question();
                long id = Long.parseLong(questions.getString("id"));
                String questionText = questions.getString("question");
                double assessment = Double.parseDouble(questions.getString("assessment"));
                question.setAssessment(assessment);
                question.setQuestion(questionText);
                question.setId(id);

                String sqlTrueAnswer = "SELECT id_answer FROM trueAnswers WHERE id_question=?";
                PreparedStatement preparedStatement1 = this.getPreparedStatement(sqlTrueAnswer);
                preparedStatement1.setLong(1, question.getId());
                ResultSet rs = preparedStatement1.executeQuery();
                while (rs.next()) {
                    long idTrueAnswer = rs.getInt("id_answer");
                    question.setTrueNumber(idTrueAnswer);
                }
                preparedStatement1.getConnection().close();
               // preparedStatement1.close();
                //rs.close();

                String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
                PreparedStatement preparedStatement2 = this.getPreparedStatement(sqlAnswers);
                preparedStatement2.setLong(1, question.getId());
                ResultSet answers = preparedStatement2.executeQuery();
                preparedStatement2.getConnection().close();
               // preparedStatement2.close();
              //  answers.close();

                String sqlAnswerText = "SELECT * FROM answers WHERE id=?";
                PreparedStatement preparedStatement3 = this.getPreparedStatement(sqlAnswerText);
                while (answers.next()) {
                    int idAnswer = Integer.parseInt(answers.getString("answer_id"));
                    question.getIdAnswers().add(idAnswer);
                    preparedStatement3.setLong(1, idAnswer);
                    ResultSet answerText = preparedStatement3.executeQuery();
                    while (answerText.next()) {
                        String answerContent = answerText.getString("answer");
                        question.getAnswers().add(answerContent);
                    }
                }
                preparedStatement3.getConnection().close();
                BankQuestions.getInstance().getQuestions().add(question);
            }
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}



