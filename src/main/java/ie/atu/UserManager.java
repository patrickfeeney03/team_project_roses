package ie.atu;

import java.sql.*;

public class UserManager {
    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }
}
