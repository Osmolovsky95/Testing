package servlets.xml;



import servlets.json.Utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class AddServletXML extends HttpServlet {
    private Charset utf8 = StandardCharsets.UTF_8;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        StringBuffer stringBuffer= Utils.streamToString(req.getInputStream());
        try {
            File file = new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\json\\studentsXML.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stringBuffer.toString().getBytes(utf8));
            fileOutputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}