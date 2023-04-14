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

            // .executeUpdate() returns the number of rows affected.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String updateSQL = "UPDATE user SET email = ?, password = ?, name = ?, role = ?, address = ?, phone = ?," +
                "age = ? WHERE userID = ?";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, user.getUser_email());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.setString(3, user.getUser_Name());
            preparedStatement.setString(4, user.getUser_role());
            preparedStatement.setString(5, user.getUser_Address());
            preparedStatement.setString(6, user.getUser_Phone());
            preparedStatement.setInt(7, user.getUser_Age());
            preparedStatement.setInt(8, user.getUser_Id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeUser(User user) {
        String deleteSQL = "DELETE FROM  user WHERE userID = ?";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, user.getUser_Id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
