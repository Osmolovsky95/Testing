package servlets.parametrs;
import dao.AnswerDAO;
import data.AssessmentSetter;
import data.student.Student;
import dao.loaders.LoaderStudentsDAO;
import data.question.Question;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import data.question.BankQuestions;
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
                long currentAnswerId=Long.parseLong(req.getParameter("0"));
                System.out.println("id_ques "+question.getId()+"  "+student.getId()+ "  answer" +currentAnswerId);
                AnswerDAO.addStudentAnswers(question,student,currentAnswerId);
                if (currentAnswerId == currentQuestion.getTrueNumber()) {
                    double assessment=(double)session.getAttribute("assessment");
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
            long currentAnswerId=Long.parseLong(req.getParameter("0"));
            AnswerDAO.addStudentAnswers(lastQuestion,student,currentAnswerId);
            if (currentAnswerId == lastQuestion.getTrueNumber()) {
                 double assessment=(double)session.getAttribute("assessment");
                 assessment+=lastQuestion.getAssessment();
                 session.setAttribute("assessment",assessment);
                 new AssessmentSetter(student,assessment);
            }
            PrintWriter pw = resp.getWriter();
            pw.println("Your assessment is  " + session.getAttribute("assessment"));
            new LoaderStudentsDAO().load();
            session.invalidate();
            for (Question question:list){
                System.out.println(question);
            }
        }
    }
    }


