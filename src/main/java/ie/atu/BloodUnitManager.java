package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BloodUnitManager {

    // Method to select the blood to be donated with the closest expiration date
    public static List<Integer> getBestBloodByDate(List<String> compatibleTypesForRecipient) {
        BloodUnit bloodUnit = null;
        BloodType bloodType = null;
        List<Integer> IDsToReturn = null;

        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM donated_blood " +
        "WHERE DATE_ADD(donation_date, INTERVAL 2 MONTH) >= CURDATE() AND blood_typesID = ? " +
        "ORDER BY DATEDIFF(DATE_ADD(donation_date, INTERVAL 2 MONTH), CURDATE()) ASC " +
        "LIMIT 1";
        // Add where

        IDsToReturn = new ArrayList<>();
        for (int i = 0; i < compatibleTypesForRecipient.size(); i++) {
            try (Connection connection = DBConnectionUtils.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
                preparedStatement.setInt(1,
                        BloodManager.get_blood_typeID(compatibleTypesForRecipient.get(i)));
                System.out.println(BloodManager.get_blood_typeID(compatibleTypesForRecipient.get(i)));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int donatedBloodID = resultSet.getInt("donated_blood_ID");
                    IDsToReturn.add(donatedBloodID);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return IDsToReturn;
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
}
