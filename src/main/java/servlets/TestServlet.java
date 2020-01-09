package servlets;
import data.Student;
import question.Question;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import question.BankQuestions;


public class TestServlet extends HttpServlet {

    int countQuestion = 1;
    private Student student;
    private double currentAssesment = 0;
    int session = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 09.01.2020 Оценка закрепить за сессией
        /*

            HttpSession session = req.getSession();
            student = (Student) session.getAttribute("currentStudent");
            session.setAttribute("count",session);*/
        System.out.println("GET");
        if (countQuestion< BankQuestions.getInstance().getQuestions().size()) {
            Question question = BankQuestions.getInstance().getQuestions().get(countQuestion);
            req.setAttribute("question", question.getQuestion());
            req.setAttribute("answer0", question.getAnswers().get(0));
            req.setAttribute("answer1", question.getAnswers().get(1));
            req.setAttribute("answer2", question.getAnswers().get(2));
            req.setAttribute("answer3", question.getAnswers().get(3));

            req.setAttribute("answer_id_0", question.getIdAnswers().get(0));
            req.setAttribute("answer_id_1", question.getIdAnswers().get(1));
            req.setAttribute("answer_id_2", question.getIdAnswers().get(2));
            req.setAttribute("answer_id_3", question.getIdAnswers().get(3));
            req.setAttribute("nameButton", "Ответить");
            long trueNumber = question.getTrueNumber();

            System.out.println(req.getParameter("0") + " выбранный");
            Question currentQuestion = BankQuestions.getInstance().getQuestions().get(countQuestion - 1);
            System.out.println(currentQuestion.getTrueNumber() + " правильный");
            System.out.println(currentQuestion.getTrueNumber());
            if (countQuestion > 1) {
                if (Long.parseLong(req.getParameter("0")) == currentQuestion.getTrueNumber()) {
                    currentAssesment += currentQuestion.getAssessment();
                }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);
        countQuestion++;
        }
        else {
            Question lastQuestion = BankQuestions.getInstance().getQuestions().get(BankQuestions.getInstance().getQuestions().size() - 1);
            currentAssesment = currentAssesment + lastQuestion.getAssessment();
            PrintWriter pw = resp.getWriter();
            pw.println("Your assessment is  " + currentAssesment);
        }

        System.out.println(currentAssesment+"текущая оценка");

    }
    }


