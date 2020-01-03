package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DAO {
     String url="jdbc:postgresql://localhost:5432/Testing";
     String name="postgres";
     String password = "postgres";

   default  PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,name,password);
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        return preparedStatement;
    }
}
