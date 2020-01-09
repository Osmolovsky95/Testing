package data;

import DAO.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssessmentSetter implements DAO {
    private Student student;
    private double assessment;
    private String sql="INSERT INTO studentsResult(testName,id_student,assessment) values (?,?,?)";
    // TODO: 09.01.2020 Созддать таблицу бд
    public AssessmentSetter(Student student, double assessment) {
        this.student=student;
        this.assessment=assessment;
        student.getAssessments().add(assessment);
        this.setToDB(student,assessment);
    }

    public void setToDB(Student student,double assessment){
        String testName="TestName";
        long id=student.getId();
       try {
           PreparedStatement preparedStatement = this.getPreparedStatement(sql);
           preparedStatement.setString(1, testName);
           preparedStatement.setLong(2, id);
           preparedStatement.setDouble(3, assessment);
           System.out.println(preparedStatement.execute());
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }

    }
}
