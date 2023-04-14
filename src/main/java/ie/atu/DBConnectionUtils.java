package ie.atu;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBConnectionUtils {

    // Insert the link of the database.
    // Replaces the placeholders!
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "someSadPassword...883";
    private static final DataSource dataSource;


    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser(USERNAME);
        mysqlDataSource.setPassword(PASSWORD);
        dataSource = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // add some function to close the connection?
}
