package Data;

import Question.Question;
import Question.BankQuestions;
import java.sql.*;

public class QuestionDAO implements DAO {

    public static long addQuestion(String question,double assessment) throws SQLException, ClassNotFoundException {
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
        BankQuestions.getInstance().getQuestions().add(question1);
        return id;
    }

}
