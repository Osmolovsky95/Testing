package servlets.parametrs;


import dao.loaders.LoaderQuestionsDAO;
import data.question.BankQuestions;
import data.question.Question;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ListQuestionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        BankQuestions.getInstance().getQuestions().clear();
        new LoaderQuestionsDAO().load();
        BankQuestions bankQuestions = BankQuestions.getInstance();
        if (bankQuestions.getQuestions().isEmpty()) {
            pw.println("Not questions");
        } else {
            pw.println("<html>");
            pw.println("<body>");

            for (Question question:bankQuestions.getQuestions()){
                pw.println("<p>"+question.getQuestion() + "  id:  "+ question.getId()+"</p>");
            }
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}
