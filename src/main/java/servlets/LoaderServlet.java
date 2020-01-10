package servlets;

import loaders.ILoader;
import loaders.LoaderQuestions;
import loaders.LoaderStudents;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoaderServlet extends HttpServlet {
    int count=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if(count==0) {
            ILoader loaderStudents = new LoaderStudents();
            loaderStudents.load();
            ILoader loaderQuestions = new LoaderQuestions();
            loaderQuestions.load();
            count++;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
