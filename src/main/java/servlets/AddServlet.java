package servlets;

import Data.GroupStudents;
import Data.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {



        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
            requestDispatcher.forward(req, resp);
        }

        @Override

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            GroupStudents myGroup=GroupStudents.getInstance();
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            String name = req.getParameter("name");
            String password= req.getParameter("pass");
            Student student=new Student(name,password);
            myGroup.getStudents().add(student);
            System.out.println("addServlet "+myGroup.getStudents().size());
            req.setAttribute("userName", name);
            doGet(req, resp);
        }
    }




