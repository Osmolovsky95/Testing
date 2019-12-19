package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Question.BankQuestions;
import Question.Question;

public class StudentSignInServlet extends HttpServlet {
    int countQuestion=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/studentPage.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question question=BankQuestions.getInstance().getQuestions().get(countQuestion);
        req.setAttribute("question", question.getQuestion());
        req.setAttribute("answer0",question.getAnswers().get(0));
        req.setAttribute("answer1",question.getAnswers().get(1));
        req.setAttribute("answer2",question.getAnswers().get(2));
        req.setAttribute("answer3",question.getAnswers().get(3));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);

       int trueNumber=BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
        String[]  s=req.getParameterValues(""+trueNumber);
        for (int i=0;i<s.length;i++){
           if(Integer.parseInt(s[i])==trueNumber){
               System.out.println("Верно");
               countQuestion++;
           }
        }
    }
    }

