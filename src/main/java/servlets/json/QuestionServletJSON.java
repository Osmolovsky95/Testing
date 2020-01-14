package servlets.json;

import org.json.JSONObject;
import data.question.Question;
import service.QuestionService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionServletJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer=new StringBuffer();
        String str;
        while ((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }
        JSONObject jsonObject=new JSONObject(stringBuffer.toString());
        String question=jsonObject.getString("question");
        String answer1=jsonObject.getString("answer1");
        String answer2=jsonObject.getString("answer2");
        String answer3=jsonObject.getString("answer3");
        String answer4=jsonObject.getString("answer4");
        int trueNumber=jsonObject.getInt("trueNumber");
        double assessment=jsonObject.getDouble("assessment");
        List<String> answers=new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        QuestionService questionService=new QuestionService();
        questionService.addQuestion(new Question(question,answers,assessment),trueNumber);
    }


}
