package servlets.json;

import data.student.Student;
import org.json.JSONObject;
import service.StudentService;
import test.Context;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AddServletJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        StringBuffer stringBuffer=Utils.streamToString(req.getInputStream());

             JSONObject jsonObject=new JSONObject(stringBuffer.toString());
             String studentName=jsonObject.getString("name");
             String studentPass=jsonObject.getString("pass");

             Student student=new Student(studentName,studentPass);
             Context.getInstance().getBean("studentService",StudentService.class).addStudent(student);
    }
}



