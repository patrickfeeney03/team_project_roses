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
            preparedStatement.setString(2, user.getUser_email());
            preparedStatement.setString(3, user.getUser_password());
            preparedStatement.setString(4, user.getUser_Name());
            preparedStatement.setString(5, user.getUser_role());
            preparedStatement.setString(6, user.getUser_Address());
            preparedStatement.setString(7, user.getUser_Phone());
            preparedStatement.setInt(8, user.getUser_Age());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
