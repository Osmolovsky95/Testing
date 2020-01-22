package servlets.parametrs;

import data.student.Student;
import service.StudentService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



public class AddServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
            requestDispatcher.forward(req, resp);
        }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=UTF-8");
            String name = req.getParameter("name");
            String password= req.getParameter("pass");
            Student student=new Student(name,password);
            new StudentService().addStudent(student);
            req.setAttribute("userName", name);
            doGet(req, resp);

        }
    }




