package dao;


import dao.jdbc.DAO;
import data.student.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssessmentSetter implements DAO {

    public AssessmentSetter(Student student, double assessment) {
        student.getAssessments().add(assessment);
        this.setToDB(student,assessment);
    }

    private void setToDB(Student student,double assessment){
        String testName="TestName";
        long id=student.getId();
       try {
           String sql = "INSERT INTO studentsResult(testName,id_student,assessment) values (?,?,?)";
           PreparedStatement preparedStatement = this.getPreparedStatement(sql);
           preparedStatement.setString(1, testName);
           preparedStatement.setLong(2, id);
           preparedStatement.setDouble(3, assessment);
           preparedStatement.execute();
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       }
    }
}
