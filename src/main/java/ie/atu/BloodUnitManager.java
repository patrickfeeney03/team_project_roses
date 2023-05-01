package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class BloodUnitManager {

    // Method to select the blood to be donated with the closest expiration date
    public static BloodUnit getBestBloodByDate() {
        BloodUnit bloodUnit = null;

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the blood type ID : ");
        int bloodGroup = scanner.nextInt();
        System.out.println("Enter the amount of blood units : ");
        int amount = scanner.nextInt();*/


        // MySQL code to select blood from the blood bank with the closest expiration date
        String selectBloodDate = "SELECT * FROM blood_units_date " +
        "WHERE DATE_ADD(blood_date, INTERVAL 2 MONTH) >= CURDATE() " +
        "ORDER BY DATEDIFF(DATE_ADD(blood_date, INTERVAL 2 MONTH), CURDATE()) ASC " +
        "LIMIT 5";

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBloodDate)) {
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bloodUnitsDateID = resultSet.getInt("blood_units_dateID");
                String bloodType = resultSet.getString("blood_typesID");

                // print the blood units by their primary key
                System.out.println("Blood Units Date ID: " + bloodUnitsDateID + ", Blood Type: " + bloodType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
