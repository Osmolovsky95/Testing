package servlets;

import Question.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Question.BankQuestions;
public class TestServlet extends HttpServlet {
    int countQuestion = 0;

    // TODO: 21.12.2019  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(countQuestion+"get");
        req.setAttribute("nameButton","Ответить");
        Question question= BankQuestions.getInstance().getQuestions().get(countQuestion);
        req.setAttribute("question", question.getQuestion());
        req.setAttribute("answer0",question.getAnswers().get(0));
        req.setAttribute("answer1",question.getAnswers().get(1));
        req.setAttribute("answer2",question.getAnswers().get(2));
        req.setAttribute("answer3",question.getAnswers().get(3));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);
        int trueNumber= BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
        String[] s=req.getParameterValues(""+trueNumber);
        for (int i=0;i<s.length;i++){
            if(Integer.parseInt(s[i])==trueNumber){
                System.out.println("Верно");
                break;
            }
        }
        countQuestion++;
        System.out.println(countQuestion+"countGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("nameButton","Ответить");
        Question question= BankQuestions.getInstance().getQuestions().get(countQuestion);
        req.setAttribute("question", question.getQuestion());
        req.setAttribute("answer0",question.getAnswers().get(0));
        req.setAttribute("answer1",question.getAnswers().get(1));
        req.setAttribute("answer2",question.getAnswers().get(2));
        req.setAttribute("answer3",question.getAnswers().get(3));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);

        int trueNumber= BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
        String[] s=req.getParameterValues(""+trueNumber);
        for (int i=0;i<s.length;i++){
            if(Integer.parseInt(s[i])==trueNumber){
                System.out.println("Верно");
            }
        }
        countQuestion++;
        System.out.println(countQuestion+"countPost");
    }
}
