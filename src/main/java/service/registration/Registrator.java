package service.registration;

import data.Administrator;
import dao.jdbc.DAO;
import data.IPerson;
import data.student.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registrator implements DAO {

    public IPerson registration(IPerson iPerson, SignInEnum signInEnum){
        String sql=null;
        if(signInEnum==SignInEnum.STUDENT){
            sql="SELECT * FROM students WHERE name=?";
        }
        else if (signInEnum==SignInEnum.ADMINISTRATOR) {
            sql = "SELECT * FROM  administrators WHERE name=?";
        }
        DAO registrator=new Registrator();
        IPerson person=null;
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = registrator.getPreparedStatement(sql);
            preparedStatement.setString(1, iPerson.getName());
            resultSet = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();
            if (signInEnum == SignInEnum.STUDENT) {
                while (resultSet.next()) {
                    if (iPerson.getName().equals(resultSet.getString("name")) & iPerson.getPassword().equals(resultSet.getString("password"))) {
                        person = new Student(iPerson.getName(),iPerson.getPassword(), resultSet.getLong("id"));
                    } else person = null;
                }
            } else if (signInEnum == SignInEnum.ADMINISTRATOR) {
                while (resultSet.next()) {
                    if (iPerson.getName().equals(resultSet.getString("name")) & iPerson.getPassword().equals(resultSet.getString("password"))) {
                        person = new Administrator(iPerson.getName(), iPerson.getPassword(), resultSet.getLong("id"));
                    } else person = null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
        }
    }
