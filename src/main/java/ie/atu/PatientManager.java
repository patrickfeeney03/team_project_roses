package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientManager {

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public boolean addPatient(Patient patient) {
        String insertSQL = "INSERT INTO patient_info (patient_Id, patient_email, patient_firstName, patient_lastName, " +
                "role, patient_address, patient_phone, patient_emergencyPhone, patient_age) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, patient.getPatient_email());
            preparedStatement.setString(2, patient.getPatient_firstName());
            preparedStatement.setString(3, patient.getPatient_lastName());
            preparedStatement.setString(5, patient.getPatient_address());
            preparedStatement.setString(6, patient.getPatient_phone());
            preparedStatement.setString(7, patient.getPatient_emergencyPhone());
            preparedStatement.setInt(8, patient.getPatient_age());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


        public void register(Scanner scanner) {
        System.out.print("Enter patient email: ");
        String patient_email = scanner.next();
        System.out.print("Enter patient's first name: ");
        String patient_firstName = scanner.next();
        System.out.print("Enter patient's last name: ");
        String patient_lastName = scanner.next();
        System.out.print("Enter patient's address: ");
        String patient_address = scanner.next();
        System.out.print("Enter patient's phone: ");
        String patient_phone = scanner.next();
        System.out.print("Enter patient's emergency phone: ");
        String patient_emergencyPhone = scanner.next();
        System.out.print("Enter patient's age: ");
        int patient_age = scanner.nextInt();

        // By setting the ID to 0, the auto-increment from SQL will automatically set the ID.
        Patient newPatient = new Patient(0, patient_email, patient_firstName, patient_lastName, patient_address,
                patient_phone, patient_emergencyPhone, patient_age);
        boolean wasRegistrationSuccessful = addPatient(newPatient);

        if (wasRegistrationSuccessful) {
            System.out.println("Patient's registration successful.");
        }
        else {
            System.out.println("Patient's registration failed.");
        }
    }
}
