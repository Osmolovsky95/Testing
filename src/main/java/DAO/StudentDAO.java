package DAO;
import data.GroupStudents;
import data.Student;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class StudentDAO implements DAO {

    public static void insertStudent(String name,String password) throws SQLException, ClassNotFoundException {
        String insertSQL="INSERT INTO students (name, password) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(insertSQL);
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
        String deleteSQL="DELETE FROM students where name=?";
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(deleteSQL);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.getConnection().close();

        String studentName;
        Set<Student> students=GroupStudents.getInstance().getStudents();
        for (Student student : students) {
            studentName = student.getName();
            if (studentName.equals(name)) {
                students.remove(student);
                break;
            }
        }
    }

    public static void loadStudents(){
        String selectSQL="SELECT * FROM students";
        try{
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(selectSQL);
        ResultSet resultSet=preparedStatement.executeQuery();
        preparedStatement.getConnection().close();

        while (resultSet.next()){
            long id=resultSet.getLong("id");
            String name=resultSet.getString("name");
            String password=resultSet.getString("password");
            Student student=new Student(name,password,id);
            GroupStudents.getInstance().getStudents().add(student);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void selectStudentAssessment(){

    }

}
