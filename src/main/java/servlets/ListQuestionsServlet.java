package servlets;

import question.BankQuestions;

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
        BankQuestions bankQuestions = BankQuestions.getInstance();
        int sizeQuestions = bankQuestions.getQuestions().size();
        if (bankQuestions.getQuestions().isEmpty()) {
            pw.println("Not questions");
        } else {
            pw.println("<html>");
            pw.println("<body>");

            for (int i = 0; i < sizeQuestions; i++) {
                pw.println("<p>"+ bankQuestions.getQuestions().get(i).getQuestion() + "  id:  "+bankQuestions.getQuestions().get(i).getId()+"</p>");
            }
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}
