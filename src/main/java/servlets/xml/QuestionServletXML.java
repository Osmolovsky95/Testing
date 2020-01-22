package servlets.xml;


import service.xml.parsers.ParseHelper;
import service.xml.parsers.ParserSTAX;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



public class QuestionServletXML extends HttpServlet {
   // Charset utf8 = StandardCharsets.UTF_8;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

       /* StringBuffer stringBuffer=Utils.streamToString(req.getInputStream());
        try {
            File file=new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\json\\questionXML.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stringBuffer.toString().getBytes("cp1251"));
            //List<Question> list = (ArrayList)new QuestionXML().fromXML(file);
            fileOutputStream.close();*/
           new  ParseHelper(new ParserSTAX(),req.getInputStream());

       // } catch (IOException ex) {
         //   ex.printStackTrace();
       // }
    }
}






