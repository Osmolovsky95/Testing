import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("name");
        System.out.println(name);
        String deleteQuerry=" delete from students where name='"+name+"\'";
        System.out.println(deleteQuerry);
        ////////////////////////////////////

        /////////////////////////////////////
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novacom?serverTimezone=UTC", "root", "root");
            Statement statement = connection.createStatement();
            System.out.println(  statement.executeUpdate(deleteQuerry));
        } catch (SQLException e) {
            System.out.println("No connection");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("no connection");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }
}
