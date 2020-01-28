package servlets.parametrs;

import data.student.Student;
import service.StudentService;
import test.Context;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        Student student= Context.getInstance().getBean("student",Student.class);
        student.setName(name);
        StudentService studentService=Context.getInstance().getBean("studentService",StudentService.class);
        boolean result= studentService.deleteStudent(student);
        req.setAttribute("result",result);
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

