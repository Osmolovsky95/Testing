package service;

import dao.jdbc.StudentDAO;
import dao.loaders.LoaderStudentsDAO;
import dao.spring.StudentSpringDAO;
import data.student.GroupStudents;
import data.student.Student;
import service.json.StudentJSON;
import test.Context;
import java.io.File;
import java.util.Set;

public class StudentService {

    public void addStudent(Student student){
        Context.getInstance().getBean("studentSpringDAO", StudentSpringDAO.class).insertStudent(student);
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
