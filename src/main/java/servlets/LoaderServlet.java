package servlets;

import dao.loaders.ILoader;
import dao.loaders.LoaderQuestionsSpringDAO;
import dao.loaders.LoaderStudentsDAO;
import dao.loaders.LoaderStudentsSpringDAO;
import test.Context;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoaderServlet extends HttpServlet {
   private int count=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if(count==0) {
            LoaderStudentsSpringDAO loaderStudents = Context.getInstance().getBean("loaderStudentsSpringDAO", LoaderStudentsSpringDAO.class);
            loaderStudents.load();
            LoaderQuestionsSpringDAO loaderQuestions = Context.getInstance().getBean("loaderQuestionsSpringDAO", LoaderQuestionsSpringDAO.class);
            loaderQuestions.load();
            count++;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
