package servlets.parametrs;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import data.question.Question;
import service.QuestionService;
import test.Context;


public class QuestionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/createQuestion.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String answer1=req.getParameter("answer1");
        String answer2=req.getParameter("answer2");
        String answer3=req.getParameter("answer3");
        String answer4=req.getParameter("answer4");

        String qst=req.getParameter("question");

        int trueNumber=Integer.parseInt(req.getParameter("trueNumber"));
        List <String> answers=new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        double assessment=(Double.parseDouble(req.getParameter("assessment")));
        Question myQuestion = new Question(qst,answers,assessment);
        QuestionService questionService= Context.getInstance().getBean("questionService",QuestionService.class);
        questionService.addQuestion(myQuestion,trueNumber);

        PrintWriter pw=resp.getWriter();
        pw.println("Question is added");

    }
}

