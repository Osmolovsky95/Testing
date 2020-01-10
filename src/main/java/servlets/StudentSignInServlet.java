package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import data.*;
import registration.Registrator;
import registration.SignInEnum;


public class StudentSignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/studentPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        PrintWriter pw = resp.getWriter();
        Registrator registrator=new Registrator();
        Student student =(Student)registrator.registration(name,password, SignInEnum.STUDENT);
        if(!(student==null)){
            resp.sendRedirect("TestServlet");
            HttpSession session=req.getSession();
            session.setAttribute("currentStudent",student);
        }
        else {
            pw.println("Invalid input");
        }
    }
}

