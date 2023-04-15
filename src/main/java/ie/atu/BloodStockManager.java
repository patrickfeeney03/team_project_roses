package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodStockManager {
    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public int getAvailableBloodstock(BloodType requestedBloodType) {
        int availableAmount = 0;
        String selectSQL =
                "SELECT bs.amount FROM blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id " +
                "WHERE bt.blood_group = ? AND bt.rh_factor = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, requestedBloodType.getBloodGroup());
            preparedStatement.setString(2, Character.toString(requestedBloodType.getRhFactor()));

            ResultSet resultSet = preparedStatement.executeQuery();

            if ( resultSet.next()) {
                availableAmount = resultSet.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableAmount;
    }

    public boolean updateBloodStock(BloodType bloodTypeToUpdate, int amount) {
        boolean updateSuccessful = false;
        String updateSQL =
                "" +
                "" +
                "";
        return false;
    }

}
