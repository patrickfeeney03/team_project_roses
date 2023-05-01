package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// Method to select the blood to be donated with the closest expiration date
public class BloodUnitManager {

    public static BloodStock getBestBloodByDate() {
        BloodStock bloodStock = null;

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


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodStock;
    }
}
