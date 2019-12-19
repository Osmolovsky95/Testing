package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AdministrarorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/administratorPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password= req.getParameter("pass");
      //  String insertQuerry= "insert into administrators (name,password) values"+"(\'"+name+"\', \'"+password+"\')";
       // System.out.println(insertQuerry);
        ////////////////////////////////////////////
        //Insert to DataBase
        ////////////////////////////////////////////
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novacom?serverTimezone=UTC", "root", "root");
            Statement statement = connection.createStatement();
           ResultSet resultSet= statement.executeQuery("select * from administrators ");
           while(resultSet.next()){
               if(resultSet.getString("name").equals(name) & resultSet.getString("password").equals(password)){
                   RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/updateDB.jsp");
                   requestDispatcher.forward(req, resp);
               }
               else{
                   PrintWriter printWriter=resp.getWriter();
                   printWriter.println("Invalid");
                  break;
               }
           }
            connection.close();
        } catch (SQLException e) {
            System.out.println("No connection");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("no connection");
        }
    }
}
