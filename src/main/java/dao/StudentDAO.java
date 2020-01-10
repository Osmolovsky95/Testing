package dao;
import data.GroupStudents;
import data.Student;
import loaders.LoaderStudents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentDAO implements DAO {

    public static void insertStudent(String name, String password)  {
        long id = 0;
        String insertSQL = "INSERT INTO students (name, password) Values (?,?) RETURNING id";
        try{
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(insertSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        preparedStatement.getConnection().close();
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
        Student student = new Student(name, password, id);
        GroupStudents.getInstance().getStudents().add(student);
    }

    public static boolean deleteStudent(String name) {
        boolean result=false;
        try {

            String deleteSQL = "DELETE FROM students where name=?";
            PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(deleteSQL);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            preparedStatement.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String studentName;
        Set<Student> students = GroupStudents.getInstance().getStudents();
        for (Student student : students) {
            studentName = student.getName();
            if (studentName.equals(name)) {
                students.remove(student);
                result=true;
                break;
            }
        }
        new LoaderStudents().load();
        System.out.println(result);
        return result;
    }




    public static List<Double>  selectStudentAssessment(Student student){
        String sql="SELECT * FROM studentsResult where id_student=?";
        List<Double> listAssessment=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(sql);
            preparedStatement.setLong(1, student.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                double assessment=rs.getDouble("assessment");
                listAssessment.add(assessment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listAssessment;
    }

}
