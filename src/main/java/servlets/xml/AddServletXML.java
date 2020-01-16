package servlets.xml;



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
        InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str);
        }
        inputStreamReader.close();
        bufferedReader.close();
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