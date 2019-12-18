import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.sql.DriverManager.getConnection;


public class AddServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
            requestDispatcher.forward(req, resp);
        }

        @Override

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.setCharacterEncoding("UTF-8");
resp.setContentType("text/html;charset=UTF-8");
            String name = req.getParameter("name");
            int id= Integer.parseInt(req.getParameter("pass"));
            String insertQuerry="insert into students (name) value ("+"\'"+name+"\'"+")";
            System.out.println(insertQuerry);
            ////////////////////////////////////////////
            //Insert to DataBase
            ////////////////////////////////////////////
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novacom?serverTimezone=UTC", "root", "root");
                Statement statement = connection.createStatement();
                statement.execute(insertQuerry);
            } catch (SQLException e) {
                System.out.println("No connection");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("no connection");
            }
           ///////////////////////////////////////////////
            Student user = new Student(name, id);
            Model model = Model.getInstance();
            model.add(user);
            req.setAttribute("userName", name);
            doGet(req, resp);
        }
    }




