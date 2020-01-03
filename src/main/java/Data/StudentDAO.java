package Data;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class StudentDAO implements DAO {
    /*select question,answer from trueAnswers inner join questions on
     id_question=questions.id join answers on id_answer=answers.id*/

    // TODO: 02.01.2020 getgeneratedkey 
    public static void insertStudent(String name,String password) throws SQLException, ClassNotFoundException {
        String insertQuerry="INSERT INTO students (name, password) Values (?,?) RETURNING id";
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(insertQuerry);
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
        PreparedStatement preparedStatement = new StudentDAO().getPreparedStatement(deleteQuerry);
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

}
