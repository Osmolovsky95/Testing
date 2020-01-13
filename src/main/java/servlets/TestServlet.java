package servlets;
import data.AssessmentSetter;
import data.Student;
import dao.loaders.LoaderStudentsDAO;
import question.Question;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import question.BankQuestions;
import test.Test;


public class TestServlet extends HttpServlet {

    private List<Question> list=new Test().generateNumberQuestion(BankQuestions.getInstance().getQuestions());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int countSession=1;
        double assessmentCurrent=0;

        HttpSession session=req.getSession();
        Student student=(Student) session.getAttribute("currentStudent");

        if(session.getAttribute("count")==null){
            session.setAttribute("count",countSession);
            session.setAttribute("assessment",assessmentCurrent);
        }

        int countQuestion=(int)session.getAttribute("count");

        if (countQuestion < 11) {
            Question question = list.get(countQuestion);
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
            Question currentQuestion = list.get(countQuestion - 1); //countQuestion

            if (countQuestion > 1) {
                if (Long.parseLong(req.getParameter("0")) == currentQuestion.getTrueNumber()) {
                    double assessment=(double)session.getAttribute("assessment");
                    System.out.println(req.getParameter("0")+" ответ");
                    assessment+=currentQuestion.getAssessment();
                    session.setAttribute("assessment",assessment);
                }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);
        countSession=(int)session.getAttribute("count");
        session.setAttribute("count",countSession+1);
        }
        else {
            Question lastQuestion = list.get(list.size() - 1);
            if (Long.parseLong(req.getParameter("0")) == lastQuestion.getTrueNumber()) {
                 double assessment=(double)session.getAttribute("assessment");
                 assessment+=lastQuestion.getAssessment();
                 session.setAttribute("assessment",assessment);
                 new AssessmentSetter(student,assessment);
            }
            PrintWriter pw = resp.getWriter();
            pw.println("Your assessment is  " + session.getAttribute("assessment"));
            new LoaderStudentsDAO().load();
            session.invalidate();
        }
    }
    }


