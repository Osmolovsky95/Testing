package data;

import dao.DAO;
import data.student.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssessmentSetter implements DAO {
    private Student student;
    private double assessment;
    private String sql="INSERT INTO studentsResult(testName,id_student,assessment) values (?,?,?)";

    public AssessmentSetter(Student student, double assessment) {
        this.student=student;
        this.assessment=assessment;
        student.getAssessments().add(assessment);
        this.setToDB(student,assessment);
    }

    private void setToDB(Student student,double assessment){
        String testName="TestName";
        long id=student.getId();
       try {
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
