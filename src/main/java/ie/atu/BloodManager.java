package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BloodManager {
    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public boolean requestBlood(BloodType requestedBloodType, int amount) {
        int availableAmount = getAvailableBloodstock(requestedBloodType);

        if (amount <= availableAmount) {
            updateBloodStock(requestedBloodType, -amount);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean recordDonation(BloodType donatedBloodType, int amount) {
        int existingBloodStock = getAvailableBloodstock(donatedBloodType);

        if (amount <= 0) {
            return false;
        }

        return updateBloodStock(donatedBloodType, amount);
    }

    public List<BloodStock> getStock() {
        List<BloodStock> bloodStockList = new ArrayList<>();

        String selectSQL =
                "SELECT bs.amount, bt.blood_group, bt.rh_factor " +
                "FROM blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                String bloodGroup = resultSet.getString("blood_group");
                char rhFactor = resultSet.getString("rh_factor").charAt(0);
                BloodType bloodType = new BloodType(bloodGroup, rhFactor);
                BloodStock bloodStock = new BloodStock(bloodType, amount);
                bloodStockList.add(bloodStock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodStockList;
    }

    public int getAvailableBloodstock(BloodType bloodTypeAvailability) {
        int availableAmount = 0;
        String selectSQL =
                "SELECT bs.amount FROM blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id " +
                "WHERE bt.blood_group = ? AND bt.rh_factor = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, bloodTypeAvailability.getBloodGroup());
            preparedStatement.setString(2, Character.toString(bloodTypeAvailability.getRhFactor()));

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
                "UPDATE blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id " +
                "SET bs.amount = bs.amount + ? " +
                "WHERE bt.blood_group = ? AND bt.rh_factor = ?";
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, bloodTypeToUpdate.getBloodGroup());
            preparedStatement.setString(3, Character.toString(bloodTypeToUpdate.getRhFactor()));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                updateSuccessful = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateSuccessful;
    }
}
