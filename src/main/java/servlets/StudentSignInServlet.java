package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import Data.GroupStudents;
import Data.LoaderDB;
import Data.Student;


public class StudentSignInServlet extends HttpServlet {

static {
    Student student = new Student("1", "1", 1000);
    GroupStudents.getInstance().getStudents().add(student);

    LoaderDB loaderDB=new LoaderDB();
    loaderDB.createQuestionsFromDB();
}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/studentPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        GroupStudents groupStudents = GroupStudents.getInstance();
        Iterator<Student> iterator = groupStudents.getStudents().iterator();
        Student student;
        PrintWriter pw = resp.getWriter();

        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getName().equals(name) & student.getPassword().equals(password)) {
               resp.sendRedirect("TestServlet");
                HttpSession session=req.getSession();
                session.setAttribute("currentStudent",student);
            } else {
                pw.println("Invalid input");
            }
        }
    }
}

