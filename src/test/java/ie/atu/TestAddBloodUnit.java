package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestAddBloodUnit {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("A", '+');
        BloodUnit bloodUnit = new BloodUnit(bloodType); // Holds date
        PatientManager patientManager = new PatientManager();

        Patient patient = patientManager.getPatientByID(6);
        System.out.println(patient);

        // Add unit to blood_units_date table
        String addIntoUnitsSQL =
                "INSERT INTO blood_units_date (blood_typesID, donorID, blood_date) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addIntoUnitsSQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 3);
            preparedStatement.setString(3, bloodUnit.getDate());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("rowsAffected: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update the blood_stock table
        String updateBlood_stock = "UPDATE blood_stock bs " +
                "LEFT JOIN ( " +
                "SELECT blood_typesID, COUNT(*) AS count_units " +
                "FROM blood_units_date " +
                "GROUP BY blood_typesID " +
                ") bud ON bs.blood_type_id = bud.blood_typesID " +
                "SET bs.amount = COALESCE(bud.count_units, 0) " +
                "WHERE bs.blood_type_id BETWEEN 1 AND 8;";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateBlood_stock)) {

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
