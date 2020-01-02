package Data;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class TestingDAO {
    static String url="jdbc:postgresql://localhost:5432/Testing";
    static String name="postgres";
    static String password = "postgres";

    // TODO: 02.01.2020 getgeneratedkey 
    public static void insertStudent(String name,String password) throws SQLException, ClassNotFoundException {
        String insertQuerry="INSERT INTO students (name, password) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement = TestingDAO.getPreparedStatement(insertQuerry);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs= preparedStatement.executeQuery();
        long id=0;
         while (rs.next()){
          id= rs.getInt(1);
      }
        preparedStatement.getConnection().close();
        Student student=new Student(name,password,id);
        GroupStudents.getInstance().getStudents().add(student);
    }

    public static void deleteStudent(String name) throws SQLException, ClassNotFoundException {
        String deleteQuerry="DELETE FROM students where name=?";
        PreparedStatement preparedStatement = TestingDAO.getPreparedStatement(deleteQuerry);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.getConnection().close();

        String studentName;
        List<Student> students=GroupStudents.getInstance().getStudents();
        Iterator<Student> iterator=students.iterator();
        while (iterator.hasNext()) {
            Student student=iterator.next();
            studentName = student.getName();
            if (studentName.equals(name)) {
                students.remove(student);
                break;
            }
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,name,password);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement;
    }
}