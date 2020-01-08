package servlets;
import Data.Student;
import Question.Question;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import Question.BankQuestions;


public class TestServlet extends HttpServlet {

    int countQuestion = 0;
    private Student student;
    private double currentAssesment = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 08.01.2020  разбираться с проверкой (опережение правильного ответа
        System.out.println("GET");
        if (countQuestion < BankQuestions.getInstance().getQuestions().size() - 1) {
            resp.setCharacterEncoding("UTF-8");
            req.setAttribute("nameButton", "Ответить");
            HttpSession session = req.getSession();
            student = (Student) session.getAttribute("currentStudent");
            Question question = BankQuestions.getInstance().getQuestions().get(countQuestion-1);
            req.setAttribute("question", question.getQuestion());
            req.setAttribute("answer0", question.getAnswers().get(0));
            req.setAttribute("answer1", question.getAnswers().get(1));
            req.setAttribute("answer2", question.getAnswers().get(2));
            req.setAttribute("answer3", question.getAnswers().get(3));

            req.setAttribute("answer_id_0", question.getIdAnswers().get(0));
            req.setAttribute("answer_id_1", question.getIdAnswers().get(1));
            req.setAttribute("answer_id_2", question.getIdAnswers().get(2));
            req.setAttribute("answer_id_3", question.getIdAnswers().get(3));

            System.out.println(question.getTrueNumber()+" правильный ответ");
            System.out.println(req.getParameter("0")+ "  параметр");


            countQuestion++;
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
            requestDispatcher.forward(req, resp);

          /*  long trueNumber= BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
            System.out.println(trueNumber+" правильный id");
            System.out.println(req.getParameter("0")+ " выбранный");
            if (Long.parseLong(req.getParameter("0")) == question.getTrueNumber()) {
                System.out.println("Верно");
                currentAssesment = currentAssesment + question.getAssessment();
                student.getAssessments().add(currentAssesment);
            } else {
                PrintWriter printWriter = resp.getWriter();
                printWriter.println(currentAssesment);
            }*/
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          /*  System.out.println(req.getParameter("0"));
            System.out.println("POST");
            countQuestion++;
            resp.setCharacterEncoding("UTF-8");
            if (countQuestion < BankQuestions.getInstance().getQuestions().size()-1) {
                req.setAttribute("nameButton", "Ответить");

               Question question = BankQuestions.getInstance().getQuestions().get(countQuestion);
                req.setAttribute("question", question.getQuestion());
                req.setAttribute("answer0", question.getAnswers().get(0));
                req.setAttribute("answer1", question.getAnswers().get(1));
                req.setAttribute("answer2", question.getAnswers().get(2));
                req.setAttribute("answer3", question.getAnswers().get(3));

                req.setAttribute("idTrueAnswer", question.getTrueNumber());

                req.setAttribute("answer_id_0", question.getIdAnswers().get(0));
                req.setAttribute("answer_id_1", question.getIdAnswers().get(1));
                req.setAttribute("answer_id_2", question.getIdAnswers().get(2));
                req.setAttribute("answer_id_3", question.getIdAnswers().get(3));


        for (long id: question.getIdAnswers()){
            System.out.println(id);
        }


                long trueNumber = question.getTrueNumber();
                System.out.println(trueNumber + " правильный id");
                System.out.println(req.getParameter("0"));

            }
            else {
                Question lastQuestion = BankQuestions.getInstance().getQuestions().get(BankQuestions.getInstance().getQuestions().size() - 1);
                currentAssesment = currentAssesment + lastQuestion.getAssessment();
                PrintWriter pw = resp.getWriter();
                pw.println("Your assessment is  " + currentAssesment);
                student.getAssessments().add(currentAssesment);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
            requestDispatcher.forward(req, resp);
        }*/
    }
}
