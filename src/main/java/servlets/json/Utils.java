package servlets.json;

import data.student.Student;
import org.json.JSONObject;
import service.StudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static StringBuffer streamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str);
        }
        return stringBuffer;
    }
}
