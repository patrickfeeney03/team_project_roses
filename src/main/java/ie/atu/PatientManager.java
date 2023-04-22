package ie.atu;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PatientManager {

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public void register(Scanner scanner) {
        System.out.print("Enter patient's first name: ");
        String patient_firstName = scanner.next();
        System.out.print("Enter patient's last name: ");
        String patient_lastName = scanner.next();
        System.out.print("Enter patient's age: ");
        int patient_age = scanner.nextInt();
        System.out.print("Enter patient's DOB: ");
        String patient_DOB = scanner.nextLine();
        System.out.print("Enter patient email: ");
        String patient_email = scanner.next();
        System.out.print("Enter patient's address: ");
        String patient_address = scanner.next();
        System.out.print("Enter patient's phone: ");
        String patient_phone = scanner.next();
        System.out.print("Enter patient's emergency phone: ");
        String patient_emergencyPhone = scanner.next();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDate.parse(patient_DOB);
        } catch (ParseException e) {
            System.out.println("Date Error");
            e.printStackTrace();
        }

        // By setting the ID to 0, the auto-increment from SQL will automatically set the ID.
        Patient newPatient = new Patient(0, patient_firstName, patient_lastName, patient_age, patient_DOB,
                patient_email, patient_address, patient_phone, patient_emergencyPhone);
        boolean wasRegistrationSuccessful = addPatient(newPatient);

        if (wasRegistrationSuccessful) {
            System.out.println("Patient's registration successful.");
        }
        else {
            System.out.println("Patient's registration failed.");
        }
    }

    public boolean addPatient(Patient patient) {
        String insertSQL = "INSERT INTO patient_info (patientID, patientFirstName, patientLastName, patientAge," +
                "patientDOB, patientEmail, patientAddress, patientPhone, patientEmergencyPhone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, patient.getPatient_Id());
            preparedStatement.setString(2, patient.getPatient_firstName());
            preparedStatement.setString(3, patient.getPatient_lastName());
            preparedStatement.setInt(4, patient.getPatient_age());
            preparedStatement.setString(5, patient.getPatient_DOB());
            preparedStatement.setString(6, patient.getPatient_email());
            preparedStatement.setString(7, patient.getPatient_address());
            preparedStatement.setString(8, patient.getPatient_phone());
            preparedStatement.setString(9, patient.getPatient_emergencyPhone());

            // .executeUpdate() returns the number of rows affected.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePatient(Patient patient) {
        String updateSQL = "UPDATE patient_info SET patientFirstName = ?, patientLastName = ?, patientAge = ?, patientDOB = ?, " +
                "patientEmail = ?, patientAddress = ?, patientPhone = ?, patientEmergencyPhone = ? WHERE patientID = ?";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, patient.getPatient_firstName());
            preparedStatement.setString(2, patient.getPatient_lastName());
            preparedStatement.setInt(3, patient.getPatient_age());
            preparedStatement.setString(4, patient.getPatient_DOB());
            preparedStatement.setString(5, patient.getPatient_email());
            preparedStatement.setString(6, patient.getPatient_address());
            preparedStatement.setString(7, patient.getPatient_phone());
            preparedStatement.setString(8, patient.getPatient_emergencyPhone());
            preparedStatement.setInt(9, patient.getPatient_Id());

            // .executeUpdate() returns the number of rows affected.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removePatient(Patient patient) {
        String deleteSQL = "DELETE FROM patient_info WHERE patientID = ?";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, patient.getPatient_Id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Patient getPatientByID(int patientID) {
        Patient patient = null;
        String selectSQL = "SELECT * FROM patient_info WHERE patientID = ?";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                patient = new Patient();
                patient.setPatient_Id(resultSet.getInt("patientID"));
                patient.setPatient_firstName(resultSet.getString("patientFirstName"));
                patient.setPatient_lastName(resultSet.getString("patientLastName"));
                patient.setPatient_age(resultSet.getInt("patientAge"));
                patient.setPatient_DOB(resultSet.getString("patientDOB"));
                patient.setPatient_email(resultSet.getString("patientEmail"));
                patient.setPatient_address(resultSet.getString("patientAddress"));
                patient.setPatient_phone(resultSet.getString("patientPhone"));
                patient.setPatient_emergencyPhone(resultSet.getString("patientEmergencyPhone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Patient getPatientInfo() {
        Patient patient = null;
        String selectAllSQL = "SELECT u.*, e.* " +
                "FROM patient_info u " +
                "JOIN patient_medical_data e ON u.patientID = e.patientID";

        try (Connection connection = DBConnectionUtils.getConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(selectAllSQL)) {

            while (resultSet.next()) {
                patient = new Patient();
                patient.setPatient_Id(resultSet.getInt("patientID"));
                patient.setPatient_firstName(resultSet.getString("patientFirstName"));
                patient.setPatient_lastName(resultSet.getString("patientLastName"));
                patient.setPatient_age(resultSet.getInt("patientAge"));
                patient.setPatient_DOB(resultSet.getString("patientDOB"));
                patient.setPatient_email(resultSet.getString("patientEmail"));
                patient.setPatient_address(resultSet.getString("patientAddress"));
                patient.setPatient_phone(resultSet.getString("patientPhone"));
                patient.setPatient_emergencyPhone(resultSet.getString("patientEmergencyPhone"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
