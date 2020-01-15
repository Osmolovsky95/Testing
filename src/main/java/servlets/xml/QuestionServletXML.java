package servlets.xml;


import service.xml.QuestionXML;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class QuestionServletXML extends HttpServlet {
    Charset utf8 = Charset.forName("UTF-8");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str);
        }
        try {
            File file=new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\json\\questionXML.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stringBuffer.toString().getBytes("cp1251"));
            //List<Question> list = (ArrayList)new QuestionXML().fromXML(file);
           new QuestionXML().fromXML(file);
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}





