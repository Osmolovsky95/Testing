package servlets;

import dao.loaders.ILoader;
import dao.loaders.LoaderQuestionsDAO;
import dao.loaders.LoaderStudentsDAO;
import data.question.BankQuestions;
import data.question.Question;
import service.xml.QuestionXML;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LoaderServlet extends HttpServlet {
   private int count=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if(count==0) {
            ILoader loaderStudents = new LoaderStudentsDAO();
            loaderStudents.load();
            ILoader loaderQuestions = new LoaderQuestionsDAO();
            loaderQuestions.load();
            count++;
                //new QuestionXML().toXML(BankQuestions.getInstance().getQuestions());
            new QuestionXML().fromXML(new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\json\\questionXML.xml"));


        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
