package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Question.BankQuestions;
import Question.Question;

public class QuestionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/createQuestion.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question = req.getParameter("question");
        String answer1 = req.getParameter("answer1");
        String answer2 = req.getParameter("answer2");
        String answer3 = req.getParameter("answer3");
        String answer4 = req.getParameter("answer4");
        double assessment = Double.parseDouble(req.getParameter("assessment"));
        int trueNumber = Integer.parseInt(req.getParameter("trueNumber"));
        int id = Integer.parseInt(req.getParameter("id"));

        BankQuestions bankQuestions= BankQuestions.getInstance();
        Question question1=new Question();
        question1.getAnswers().add(req.getParameter("answer1"));
        question1.getAnswers().add(req.getParameter("answer2"));
        question1.getAnswers().add(req.getParameter("answer3"));
        question1.getAnswers().add(req.getParameter("answer4"));
        question1.setQuestion(req.getParameter("question"));
        question1.setTrueNumber(Integer.parseInt(req.getParameter("trueNumber")));
        question1.setAssessment(Double.parseDouble(req.getParameter("assessment")));
        question1.setId(Question.count+1);
        bankQuestions.getQuestions().add(question1);
        PrintWriter pw=resp.getWriter();
        pw.println("<html>");
        pw.println("<h1>"+bankQuestions.getQuestions().get(0)+"<h1>");
        pw.println("</html>");
    }
}

