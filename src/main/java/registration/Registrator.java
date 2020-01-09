package registration;

import data.Administrator;
import DAO.DAO;
import data.IPerson;
import data.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registrator implements DAO {

    public IPerson registration(String name, String password, SignInEnum signInEnum){
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
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (signInEnum == SignInEnum.STUDENT) {
                while (resultSet.next()) {
                    if (name.equals(resultSet.getString("name")) & password.equals(resultSet.getString("password"))) {
                        person = new Student(name, password, resultSet.getLong("id"));
                    } else person = null;
                }
            } else if (signInEnum == SignInEnum.ADMINISTRATOR) {
                while (resultSet.next()) {
                    if (name.equals(resultSet.getString("name")) & password.equals(resultSet.getString("password"))) {
                        person = new Administrator(name, password, resultSet.getLong("id"));
                    } else person = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
        }
    }
