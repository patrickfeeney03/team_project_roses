package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;


public class BloodUnitManager {

    // Method to select the blood to be donated with the closest expiration date
    public static int getBestBloodByDate(String compatibleTypeForRecipient) {

        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM donated_blood " +
        "WHERE DATE_ADD(donation_date, INTERVAL 2 MONTH) >= CURDATE() AND blood_typesID = ? " +
        "ORDER BY DATEDIFF(DATE_ADD(donation_date, INTERVAL 2 MONTH), CURDATE()) ASC " +
        "LIMIT 1";
        // Add where

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
            preparedStatement.setInt(1,
                    BloodManager.get_blood_typeID(compatibleTypeForRecipient));
            System.out.println(BloodManager.get_blood_typeID(compatibleTypeForRecipient));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("donated_blood_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
}
