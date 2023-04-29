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
        /*INSERT INTO blood_units_date (blood_typesID, donorID, blood_date)
        VALUES (1, 1, '2023-04-29');
         */
    }

}
