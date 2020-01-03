package Data;
import Question.Answer;
import java.sql.*;

public class AnswerDAO implements DAO {

    public static long addAnswer(String answer) throws SQLException, ClassNotFoundException {
        String insertQuerry = "INSERT INTO answers (answer) Values (?) RETURNING id";
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertQuerry) ;
        preparedStatement.setString(1, answer);
        ResultSet rs = preparedStatement.executeQuery();
        long id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        Answer answer1=new Answer(id,answer);
        preparedStatement.getConnection().close();
        return id;
    }

    public static void addTrueAnswer(long answer_id,long question_id) throws SQLException, ClassNotFoundException {
        String insertQuerry = "INSERT INTO trueAnswers (id_answer,id_question) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertQuerry) ;
        preparedStatement.setLong(1, answer_id);
        preparedStatement.setLong(2, question_id);
        ResultSet rs = preparedStatement.executeQuery();
        long id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        preparedStatement.getConnection().close();


    }

}
