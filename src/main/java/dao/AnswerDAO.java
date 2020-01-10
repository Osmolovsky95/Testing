package dao;


import java.sql.*;

public class AnswerDAO implements DAO {

    public static long addAnswer(String answer) throws SQLException, ClassNotFoundException {
        String insertSQL = "INSERT INTO answers (answer) Values (?) RETURNING id";
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertSQL) ;
        preparedStatement.setString(1, answer);
        ResultSet rs = preparedStatement.executeQuery();
        long id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        preparedStatement.getConnection().close();
        return id;
    }

    public static void addTrueAnswer(long answer_id,long question_id) throws SQLException, ClassNotFoundException {
        String insertSQL = "INSERT INTO trueAnswers (id_answer,id_question) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(insertSQL) ;
        preparedStatement.setLong(1, answer_id);
        preparedStatement.setLong(2, question_id);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.getConnection().close();
    }

    public static ResultSet getAnswers(long id) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT question_answers.id,answer FROM question_Answers LEFT JOIN answers on answer_id=answers.id where question_id=?";
        PreparedStatement preparedStatement =new AnswerDAO().getPreparedStatement(selectSQL);
        preparedStatement.setLong(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        preparedStatement.getConnection().close();
        return resultSet;
    }
}
