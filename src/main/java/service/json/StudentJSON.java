package service.json;

import data.student.Student;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentJSON implements IParserJSON {
    public File toJSON(IJSON ijson) {
        File file = new File("studentJSON.json");
        try {
            Student student=(Student) ijson;
            JSONObject jsonObject = new JSONObject(student);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonObject.toString().getBytes());
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return file;
    }

    public Student fromJSON(File file) {
        Student student=null;
        try {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        while ((i = bufferedInputStream.read()) != -1) {
            stringBuffer.append((char) i);
        }
        JSONObject jsonObject = new JSONObject(stringBuffer.toString());
        String name = jsonObject.getString("name");
        String pass = jsonObject.getString("password");
        long id = jsonObject.getLong("id");
        ArrayList assessments = new ArrayList<>();
        assessments = (ArrayList) jsonObject.getJSONArray("assessments").toList();
        Double resultAssessment = jsonObject.getDouble("resultAssessment");

        student = new Student(name, pass);
        student.setId(id);
        student.setAssessments(assessments);
        student.setResultAssessment(resultAssessment);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return student;
}
}
