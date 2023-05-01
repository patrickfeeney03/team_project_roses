package ie.atu;

// Method to select the blood to be donated with the closest expiration date
public class BloodUnitManager {
    // MySQL code to select blood from the blood bank with the closest expiration date
    public String selectBloodByDate() {
        String selectBloodDate = "SELECT * FROM blood_units_date " +
        "WHERE DATE_ADD(blood_date, INTERVAL 2 MONTH) >= CURDATE() " +
        "ORDER BY DATEDIFF(DATE_ADD(blood_date, INTERVAL 2 MONTH), CURDATE()) ASC " +
        "LIMIT 5";
    }

}
