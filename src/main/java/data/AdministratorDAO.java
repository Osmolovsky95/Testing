package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAO implements DAO {

    public static void insertStudent(String name,String password) throws SQLException, ClassNotFoundException {
        String insertSQL="INSERT INTO administrators (name, password) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(insertSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs= preparedStatement.executeQuery();
        long id=0;
        while (rs.next()){
            id= rs.getInt(1);
        }
        preparedStatement.getConnection().close();
        Administrator administrator=new Administrator(name,password,id);
        GroupStudents.getInstance().getAdministrators().add(administrator);
    }
}
