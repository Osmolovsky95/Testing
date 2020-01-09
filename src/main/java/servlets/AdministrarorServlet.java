package servlets;
import data.AdministratorDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AdministrarorServlet extends HttpServlet {

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
        String querry = "select * from administrators where name=?";
        try {
            PreparedStatement preparedStatement = new AdministratorDAO().getPreparedStatement(querry);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();
            while (resultSet.next()) {
                if (password.equals(resultSet.getString("password"))) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/updateDB.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    PrintWriter pw = resp.getWriter();
                    pw.println("Invalid input");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}