package Data;

import Question.Question;
import Question.BankQuestions;
import java.sql.*;

public class QuestionDAO {
    static String url="jdbc:postgresql://localhost:5432/Testing";
    static String name="postgres";
    static String password = "postgres";
    public static void addQuestion(String question) throws SQLException, ClassNotFoundException {
        String insertQuerry="INSERT INTO questions (question) Values (?) RETURNING id";
        PreparedStatement preparedStatement = StudentDAO.getPreparedStatement(insertQuerry);
        preparedStatement.setString(1, question);
        ResultSet rs= preparedStatement.executeQuery();
        long id=0;
        while (rs.next()){
            id= rs.getInt(1);
        }
        preparedStatement.getConnection().close();
        Question question1=new Question(question,id);
        BankQuestions.getInstance().getQuestions().add(question1);
    }

    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,name,password);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement;
    }
}
