package servlets;

import data.GroupStudents;
import data.Student;
import DAO.StudentDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class DeleteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        GroupStudents groupStudents = GroupStudents.getInstance();
        try {
            StudentDAO.deleteStudent(name);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String studentName;
        Set<Student> students=groupStudents.getStudents();
        for (Student student : students) {
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

