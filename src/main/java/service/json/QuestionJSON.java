package service.json;

import org.json.JSONObject;
import data.question.Question;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QuestionJSON implements IParserJSON {
    Charset utf8 = Charset.forName("UTF-8");
    @Override
    public File toJSON(IJSON ijson) {
        File file = new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\json", "questionJSON.json");

        try {
            Question question=(Question) ijson;
            JSONObject jsonObject = new JSONObject(question);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonObject.toString().getBytes(utf8));
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Question question=(Question) this.fromJSON(file);
        System.out.println(question);
        return file;
    }

    @Override
    public IJSON fromJSON(File file) {
        Question question=new Question();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            int i;
            while ((i = bufferedReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            String questionText = jsonObject.getString("question");
            int assessment = jsonObject.getInt("assessment");
            long id = jsonObject.getLong("id");
            List<Integer> idAnswers = new ArrayList<>();
            idAnswers = (ArrayList) jsonObject.getJSONArray("idAnswers").toList();
            List <String> answers=new ArrayList<>();
            answers=(ArrayList)jsonObject.getJSONArray("answers").toList();
            long trueNumber = jsonObject.getLong("trueNumber");

            question.setId(id);
            question.setAssessment(assessment);
            question.setTrueNumber(trueNumber);
            question.setQuestion(questionText);
            question.setAnswers(answers);
            question.setIdAnswers(idAnswers);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return question;
    }
}