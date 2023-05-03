package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BloodUnitManager {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    // Method to select the blood to be donated with the closest expiration date
    public static int getBestBloodByDate(String compatibleTypeForRecipient) {

        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM donated_blood " +
        "WHERE donation_date >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) AND blood_typesID = ? AND given_away_status = 0";
        // Add where

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
            preparedStatement.setInt(1,
                    BloodManager.get_blood_typeID(compatibleTypeForRecipient));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("unit_blood_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Integer> getBestBloodByDateList(String compatibleTypeForRecipient, int limit) {
        List<Integer> idList = new ArrayList<>();
        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM donated_blood " +
                "WHERE donation_date >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) AND blood_typesID = ? AND given_away_status = 0 " +
                "LIMIT ?";
        // Add where

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
            preparedStatement.setInt(1,
                    BloodManager.get_blood_typeID(compatibleTypeForRecipient));
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idList.add(resultSet.getInt("unit_blood_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (idList.size() < limit) {
            return null;
        }
        return idList;
    }

    public static List<Integer> getBestBloodByDateList20(String compatibleTypeForRecipient) {
        List<Integer> idList = new ArrayList<>();
        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM donated_blood " +
                "WHERE donation_date >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) AND blood_typesID = ? AND given_away_status = 0 " +
                "LIMIT 20";
        // Add where

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
            preparedStatement.setInt(1,
                    BloodManager.get_blood_typeID(compatibleTypeForRecipient));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idList.add(resultSet.getInt("unit_blood_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }
    // Method to select the blood to be donated with the most amount of blood
    public static BloodStock getBestBloodByAmount() {
        BloodStock bloodStock = null;

        // MySQL code to select blood from the blood bank with the most amount
        String selectBloodAmount = "SELECT * FROM blood_stock ORDER BY amount DESC LIMIT 3;";

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodAmount)) {
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int BloodID = resultSet.getInt("id");
                int BloodTypeID = resultSet.getInt("blood_type_id");
                int BloodUnits = resultSet.getInt("amount");

                // print the blood units by their primary key
                System.out.println("Blood ID: " + BloodID + ", Blood Type ID: " + BloodTypeID + "Blood Units: " + BloodUnits);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BloodUnit getRandomValidUnit(List<BloodUnit> listBloodUnits) {
        BloodUnit bloodUnit1 = null;
        Random rand = new Random();

        int lengthOfList = listBloodUnits.size();
        int randomIndex = rand.nextInt(lengthOfList);
        bloodUnit1 = (
          listBloodUnits.get(randomIndex)
        );
        return bloodUnit1;
    }

    public static BloodType getBloodTypeWithUnitID(int id) {
        BloodType bloodType = null;

        String selectSQl = "SELECT bt.blood_group, bt.rh_factor " +
                "FROM donated_blood db " +
                "JOIN blood_types bt ON db.blood_typesID = bt.ID " +
                "WHERE db.unit_blood_ID = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQl)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet != null) {
                bloodType = new BloodType();
                bloodType.setBloodGroup(resultSet.getString("blood_group"));
                bloodType.setRhFactor(resultSet.getString("rh_factor").charAt(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodType;
    }
}
