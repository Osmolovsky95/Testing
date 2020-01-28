package servlets.json;

import org.json.JSONObject;
import data.question.Question;
import service.QuestionService;
import test.Context;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServletJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        StringBuffer stringBuffer=Utils.streamToString(req.getInputStream());
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

        QuestionService questionService= Context.getInstance().getBean("questionService",QuestionService.class);
        questionService.addQuestion(new Question(question,answers,assessment),trueNumber);
    }


}
