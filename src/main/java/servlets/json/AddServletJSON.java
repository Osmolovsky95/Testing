package servlets.json;

import data.student.Student;
import org.json.JSONObject;
import service.StudentService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AddServletJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

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
             new StudentService().addStudent(student);
    }
}



