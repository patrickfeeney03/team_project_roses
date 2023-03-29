package ie.atu;
import java.sql.*;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        // Use try catch because trying to connect may throw errors. Credentials may be wrong, etc.
        try {
            // Insert the link of the database.
            // Replaces this placeholders!
            String url = "asd";
            String userName = "admin123";
            String password = "passwordExample";
            connection = DriverManager.getConnection(url, userName, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
