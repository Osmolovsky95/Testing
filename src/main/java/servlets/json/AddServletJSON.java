package servlets.json;

import data.Student;
import org.json.JSONObject;
import org.json.JSONWriter;
import service.StudentService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AddServletJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// TODO: 13.01.2020
        InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str);
        }
             JSONObject jsonObject=new JSONObject(stringBuffer.toString());
             String studentName=jsonObject.getString("name");
             String studentPass=jsonObject.getString("pass");

             Student student=new Student(studentName,studentPass);
             StudentService studentService=new StudentService();
             studentService.addStudent(student);
    }
}



