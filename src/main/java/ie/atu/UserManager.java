package ie.atu;

import java.sql.*;

public class UserManager {
    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public boolean addUser(User user) {
        String insertSQL = "INSERT INTO user (userID, email, password, name, role, address, phone, age) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, user.getUser_Id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
