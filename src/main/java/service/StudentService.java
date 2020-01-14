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
        boolean result= StudentDAO.deleteStudent(student.getName());
        return result;
    }

    public Set<Student> getStudents(){
        new LoaderStudentsDAO().load();
        return GroupStudents.getInstance().getStudents();
    }

   public File studentToJSON(Student student){
        File file=new StudentJSON().toJSON(student);
        return file;
   }

    public Student studentFromJSON(File file){
        Student student=new StudentJSON().fromJSON(file);
        return student;
    }
}
