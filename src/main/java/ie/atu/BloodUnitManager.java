package ie.atu;

import java.util.Scanner;

// Method to select the blood to be donated with the closest expiration date
public class BloodUnitManager {

    public static BloodStock getBestBlood() {
        BloodStock bloodStock = null;
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        // MySQL code to select blood from the blood bank with the closest expiration date

        String selectBloodDate = "SELECT * FROM blood_units_date " +
        "WHERE DATE_ADD(blood_date, INTERVAL 2 MONTH) >= CURDATE() " +
        "ORDER BY DATEDIFF(DATE_ADD(blood_date, INTERVAL 2 MONTH), CURDATE()) ASC " +
        "LIMIT 5";

        return bloodStock;
    }
}
