package service;

import dao.StudentDAO;
import dao.loaders.LoaderStudentsDAO;
import data.student.GroupStudents;
import data.student.Student;
import service.json.StudentJSON;
import java.io.File;
import java.util.Set;

public class StudentService {

    public void addStudent(Student student){
        StudentDAO.insertStudent(student);
    }

    public boolean deleteStudent(Student student){
        return StudentDAO.deleteStudent(student.getName());
    }

    public Set<Student> getStudents(){
        new LoaderStudentsDAO().load();
        return GroupStudents.getInstance().getStudents();
    }

   public File studentToJSON(Student student){
       return new StudentJSON().toJSON(student);
   }

    public Student studentFromJSON(File file){
        return new StudentJSON().fromJSON(file);
    }
}
