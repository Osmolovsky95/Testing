package servlets;

import Data.GroupStudents;
import Data.Student;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DeleteServlet extends HttpServlet {
// TODO: 19.12.2019 удалять из коллекции

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        GroupStudents groupStudents = GroupStudents.getInstance();
        String studentName;
        List<Student> students=groupStudents.getStudents();
        Iterator<Student> iterator=students.iterator();
        while (iterator.hasNext()) {
            Student student=iterator.next();
            studentName = student.getName();
            if (studentName.equals(name)) {
                students.remove(student);
                System.out.println("Student "+ name+ "removed");
                break;
            }
        }
       req.setAttribute("name",name);
        doGet(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }
    }

