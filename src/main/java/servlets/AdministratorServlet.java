package servlets;


import data.Administrator;
import registration.Registrator;
import registration.SignInEnum;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdministratorServlet extends HttpServlet {

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

        Registrator registrator=new Registrator();
        Administrator administrator =(Administrator)registrator.registration(name,password, SignInEnum.ADMINISTRATOR);
        if(!(administrator==null)){
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/updateDB.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    PrintWriter pw = resp.getWriter();
                    pw.println("Invalid input");
                }
            }
        }


