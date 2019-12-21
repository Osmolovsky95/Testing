package servlets;

import Question.BankQuestions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ListQuestionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        BankQuestions bankQuestions = BankQuestions.getInstance();
        int sizeQuestions = bankQuestions.getQuestions().size();
        if (bankQuestions.getQuestions().isEmpty()) {
            pw.println("Not questions");
        } else {
            pw.println("<html>");
            pw.println("<body>");
            for (int i = 0; i < sizeQuestions; i++) {
                pw.println("<h5>" + bankQuestions.getQuestions().get(i).getQuestion() + "<h5>");
            }
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}
