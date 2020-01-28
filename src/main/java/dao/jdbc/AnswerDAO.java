package dao.jdbc;


import dao.loaders.LoaderQuestionsDAO;
import data.question.Question;
import data.student.Student;
import org.json.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerDAO implements DAO {

    public static void addAnswer(Question question,int trueNumber)  {
        String insertSQL = "INSERT INTO answers (answer) Values (?) RETURNING id";
       try {
           PreparedStatement preparedStatement = new AnswerDAO().getPreparedStatement(insertSQL);
           List<Integer> idAnswers = new ArrayList<>();
           for (String answer : question.getAnswers()) {
               preparedStatement.setString(1, answer);
               ResultSet rs = preparedStatement.executeQuery();
               int id;
               while (rs.next()) {
                   id = rs.getInt(1);
                   idAnswers.add(id);
               }
           }
           question.setIdAnswers(idAnswers);
           question.setTrueNumber(question.getIdAnswers().get(trueNumber-1));
           AnswerDAO.addTrueAnswer((question.getIdAnswers().get(trueNumber-1)),question.getId());
           preparedStatement.getConnection().close();
       } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
       }
    }

    public static void addTrueAnswer(long answer_id, long question_id)  {
        String insertSQL = "INSERT INTO trueAnswers (id_answer,id_question) Values (?,?)";
        try{
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertSQL);
        preparedStatement.setLong(1, answer_id);
        preparedStatement.setLong(2, question_id);
        preparedStatement.execute();
        preparedStatement.getConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addStudentAnswers(Question question, Student student,long currentAnswerId){
    String insertSQL="INSERT INTO studentAnswers (student_id,question_id,answer_id) VALUES(?,?,?)";
    try {
        PreparedStatement preparedStatement=new AnswerDAO().getPreparedStatement(insertSQL);
        preparedStatement.setLong(1,student.getId());
        preparedStatement.setLong(2,question.getId());
        preparedStatement.setLong(3,currentAnswerId);
        preparedStatement.execute();
        preparedStatement.getConnection().close();
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    }

    private static JSONObject  selectStudentAnswers(Question question) {
        String insertSQL = "SELECT name,answer,question,student_id,answer_id FROM studentAnswers " +
                "join questions on question_id=questions.id join answers on answer_id=answers.id " +
                "join students on student_id=students.id where studentAnswers.question_id=?";
        JSONObject jsonObject=new JSONObject();
        try {
            PreparedStatement preparedStatement = new AnswerDAO().getPreparedStatement(insertSQL);
            preparedStatement.setLong(1, question.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();
            Map<String,Object> map=new HashMap<>();
            List answers=new ArrayList();
           for (int i=0;i<question.getAnswers().size();i++){
               Map <String,String> answer=new HashMap<>();
               answer.put("id", question.getIdAnswers().get(i)+"");
               answer.put("text",question.getAnswers().get(i));
               answers.add(answer);
           }

            List<Map> students=new ArrayList();
            while (resultSet.next()) {
                Map <String,String> currentStudent=new HashMap<>();
                currentStudent.put("answer",resultSet.getString("answer_id"));
                currentStudent.put("name",resultSet.getString("name"));
                currentStudent.put("id",resultSet.getString("student_id"));
                students.add(currentStudent);
            }
            map.put("answers",answers);
            map.put("text",question.getQuestion());
            map.put("id",question.getId());
            map.put("goodAnswer",question.getTrueNumber());
            jsonObject.put("question",map);
            jsonObject.put("students",students);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject  selectStudentAnswers(String idQuestion) {
        long idd=Long.parseLong(idQuestion);
        Question question = new Question();
        try {
            String sql = "SELECT * FROM questions WHERE id=?";
            LoaderQuestionsDAO loaderQuestionsDAO=new LoaderQuestionsDAO();
            PreparedStatement preparedStatement = loaderQuestionsDAO.getPreparedStatement(sql);
            preparedStatement.setLong(1,idd);
            ResultSet questions = preparedStatement.executeQuery();

            while (questions.next()) {
                long id = Long.parseLong(questions.getString("id"));
                String questionText = questions.getString("question");
                double assessment = Double.parseDouble(questions.getString("assessment"));
                question.setAssessment(assessment);
                question.setQuestion(questionText);
                question.setId(id);

                String sqlTrueAnswer = "SELECT id_answer FROM trueAnswers WHERE id_question=?";
                PreparedStatement preparedStatement1 = loaderQuestionsDAO.getPreparedStatement(sqlTrueAnswer);
                preparedStatement1.setLong(1, question.getId());
                ResultSet rs = preparedStatement1.executeQuery();
                while (rs.next()) {
                    long idTrueAnswer = rs.getInt("id_answer");
                    question.setTrueNumber(idTrueAnswer);
                }
                preparedStatement1.getConnection().close();

                String sqlAnswers = "SELECT * FROM question_answers WHERE question_id=?";
                PreparedStatement preparedStatement2 = loaderQuestionsDAO.getPreparedStatement(sqlAnswers);
                preparedStatement2.setLong(1, question.getId());
                ResultSet answers = preparedStatement2.executeQuery();
                preparedStatement2.getConnection().close();

                String sqlAnswerText = "SELECT * FROM answers WHERE id=?";
                PreparedStatement preparedStatement3 = loaderQuestionsDAO.getPreparedStatement(sqlAnswerText);
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
            }
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return AnswerDAO.selectStudentAnswers(question);
    }

}
