package servlets;
import Data.Administrator;
import Data.GroupStudents;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


public class AdministrarorServlet extends HttpServlet {
    static {
        Administrator administrator = new Administrator("sasha", "1995");
        GroupStudents groupStudents = GroupStudents.getInstance();
        groupStudents.getAdministrators().add(administrator);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/administratorPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("pass");

        GroupStudents groupStudents = GroupStudents.getInstance();
        Iterator<Administrator> iterator = groupStudents.getAdministrators().iterator();
        Administrator administrator;
        PrintWriter pw = resp.getWriter();

        while (iterator.hasNext()) {
            administrator = iterator.next();
            if (administrator.getName().equals(name) & administrator.getPassword().equals(password)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/updateDB.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                pw.println("Invalid input");
            }

        }
    }
}