package dao.spring;

import dao.loaders.LoaderStudentsDAO;
import data.student.GroupStudents;
import data.student.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentSpringDAO {
   private JdbcTemplate jdbcTemplate;

    public StudentSpringDAO(JdbcTemplate template) {
        this.jdbcTemplate=template;
    }

    public  void insertStudent(Student student)  {
        String insertSQL = "INSERT INTO students (name, password) Values (?,?) RETURNING id";
        Map studentId=jdbcTemplate.queryForMap(insertSQL,student.getName(),student.getPassword());
        long id=(long)studentId.get("id");
        student.setId(id);
        GroupStudents.getInstance().getStudents().add(student);
    }

    public  boolean deleteStudent(String name) {
        boolean result=false;
            String deleteSQL = "DELETE FROM students where name=?";
            jdbcTemplate.update(deleteSQL,name);
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
        new LoaderStudentsDAO().load();
        return result;
    }

    public  List<Double> selectStudentAssessment(Student student){
        String sql="SELECT * FROM studentsResult where id_student=?";
        List<Double> listAssessment=new ArrayList<>();
        List<Map<String,Object>> assessmentss=   jdbcTemplate.queryForList(sql,student.getId());
         for  (Map<String,Object> assessmenttt:assessmentss){
             listAssessment.add((double)assessmenttt.get("assessment"));
         }
        return listAssessment;
    }
}
