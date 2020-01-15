package dao.loaders;

import dao.DAO;
import dao.StudentDAO;
import data.student.GroupStudents;
import data.student.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoaderStudentsDAO implements ILoader, DAO {

    @Override
    public void load() {
        String selectSQL = "SELECT * FROM students";
        try {
            PreparedStatement preparedStatement = this.getPreparedStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Student student = new Student(name, password, id);
                List<Double> assessments = StudentDAO.selectStudentAssessment(student);
                student.setAssessments(assessments);
                GroupStudents.getInstance().getStudents().add(student);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}