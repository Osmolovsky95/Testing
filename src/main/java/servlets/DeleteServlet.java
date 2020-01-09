package servlets;

import data.GroupStudents;
import data.Student;
import data.StudentDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DeleteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        GroupStudents groupStudents = GroupStudents.getInstance();
        try {
            StudentDAO.deleteStudent(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String studentName;
        List<Student> students=groupStudents.getStudents();
        Iterator<Student> iterator=students.iterator();
        while (iterator.hasNext()) {
            Student student=iterator.next();
            studentName = student.getName();
            if (studentName.equals(name)) {
                students.remove(student);
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

