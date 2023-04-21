package ie.atu;

import java.sql.Connection;
import java.sql.SQLException;

public class PatientManager {

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }
}
