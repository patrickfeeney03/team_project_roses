package ie.atu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManager {
    public static Connection getConnection() throws SQLException {
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
        String patient_DOB = scanner.next();
        System.out.print("Enter patient email: ");
        String patient_email = scanner.next();
        System.out.print("Enter patient's address: ");
        String patient_address = scanner.next();
        System.out.print("Enter patient's phone: ");
        String patient_phone = scanner.next();
        System.out.print("Enter patient's emergency phone: ");
        String patient_EmergencyPhone = scanner.next();

        /*
        We can change this later. SQL expects the date to be in the yyyy-mm-dd format,
        but the yyyy/mm/dd format also works, and the code right under this prints to
        the terminal errors even when it does work.

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDate.parse(patient_DOB);
        } catch (ParseException e) {
            System.out.println("Date Error");
            e.printStackTrace();
        }

        */

        Patient newPatient = new Patient(patient_firstName, patient_lastName, patient_age, patient_DOB,
                patient_email, patient_address, patient_phone,patient_EmergencyPhone);
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
    public boolean addPatientToDonorTable(int patientID) {
        String addToDonor = "INSERT INTO donor (corresponding_patient_id) " +
                "VALUES(?)";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addToDonor)) {
            preparedStatement.setInt(1, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e){
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
    public boolean patientExistsInDB(int patientID) {
        String searchPatient = "SELECT * FROM patient_info WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(searchPatient)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Patient getPatientInfo() {
        Patient patient = null;
        String selectAllSQL = "SELECT u.*, e.* " +
                "FROM patient_info u " +
                "JOIN patient_medical_data e ON u.patientID = e.patientID";

        try (Connection connection = DBConnectionUtils.getConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(selectAllSQL)) {

            while (resultSet.next())  {
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

    public Patient getSinglePatientInfo(int patientID) {

        Patient patient = null;
        String selectIndividualAllSQL = "SELECT u.*, e.* " +
                "FROM patient_info u " +
                "JOIN patient_medical_data e ON u.patientID = e.patientID " +
                "WHERE u.patientID = ?";

        try (Connection connection = DBConnectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectIndividualAllSQL)) {
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

    public static int getDonorIDFromPatientID(int patientID) {
        String getDonorSQL = "SELECT d.donor_unique_id " +
        "FROM donor d " +
        "JOIN patient_info pi ON d.corresponding_patient_id = pi.patientID " +
        "WHERE pi.patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getDonorSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("donor_unique_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Patient getPatientInfoAll(int userInput) {
        Patient patient = null;
        String checkPatient = "SELECT 'donor' AS table_name, patient_info.* " +
                "FROM donor " +
                "JOIN patient_info ON donor.corresponding_patient_id = patient_info.patientID " +
                "UNION " +
                "SELECT 'recipient' AS table_name, patient_info.* " +
                "FROM recipient " +
                "JOIN patient_info ON recipient.corresponding_patient_id = patient_info.patientID ";

                /*
                My suggestion for this sql query:
                SELECT 'donor' AS table_name, patient_info.* 
                FROM donor
                JOIN patient_info ON donor.corresponding_patient_id = patient_info.patientID
                WHERE patient_info.patientID = ?
                UNION
                SELECT 'recipient' AS table_name, patient_info.*
                FROM recipient
                JOIN patient_info ON recipient.corresponding_patient_id = patient_info.patientID
                WHERE patient_info.patientID = ?;
                
                In your SQL query you dont' have any '?' symbols, so you what I think you are doing is retriving ALL the info,
                instead of the info of just one patient. Which is fine, but then, if thats the case, there is no need to the
                int userInput parameter.
                I'm also deleting the System out since we won't always want to print and to make it more modular.
                
                This is new, but also ask for the patientDisease. I'll make a method to just update the patientDisease method; of a single patient.
                Using their patientID.
                */

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkPatient)) {
            //preparedStatement.setInt(1, userInput);

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

    public static List<String> getTableValues_patient_medical_data(int patientID) {
        List<String> pmdQueryResults = new ArrayList<>();
        String selectPMDSQL = "SELECT patientID, patientDisease, bloodTypeID, lastReceive, firstReceive, lastDonation, " +
                "firstDonation " +
                "FROM patient_medical_data WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectPMDSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pmdQueryResults.add(Integer.toString(resultSet.getInt("patientID")));
                pmdQueryResults.add(resultSet.getString("patientDisease"));
                pmdQueryResults.add(Integer.toString(resultSet.getInt("bloodTypeID")));
                pmdQueryResults.add(resultSet.getString("lastReceive"));
                pmdQueryResults.add(resultSet.getString("firstReceive"));
                pmdQueryResults.add(resultSet.getString("lastDonation"));
                pmdQueryResults.add(resultSet.getString("firstDonation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pmdQueryResults;
    }

    // I currently can't set the whole table since I don't know if the date columns are populated or not. And which
    // ones to populate or which ones not to. I'm writing methods for that, such as setFirstDonation, getFirstDonation
    public static boolean setTable_patient_medical_data(int patientID, String patientDisease, int bloodTypeID,
                                                String  lastReceive, String firstReceive, String lastDonation,
                                                String firstDonation) {
        String setTableSQL = "UPDATE patient_medical_data " +
                "SET patientDisease = ?, " +
                "bloodTypeId = ?, " +
                "lastReceive = ?, " +
                "firstReceive = ?, " +
                "lastDonation = ?, " +
                "firstDonation = ? " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(setTableSQL)) {
            preparedStatement.setString(1, patientDisease);
            preparedStatement.setInt(2, bloodTypeID);
            preparedStatement.setString(3, lastReceive);
            preparedStatement.setString(4, firstReceive);
            preparedStatement.setString(5, lastDonation);
            preparedStatement.setString(6, firstDonation);
            preparedStatement.setInt(7, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getFirstDonation(int patientID) {
        String firstDonationSQL = "SELECT firstDonation " +
                "FROM patient_medical_data " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(firstDonationSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("firstDonation");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Patient not found";
    }

    public static boolean setFirstDonation(int patientID, String date) {
        String setFirstDonationSQL = "UPDATE patient_medical_data " +
                "SET firstDonation = ? " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(setFirstDonationSQL)) {
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setLastDonation(int patientID, String date) {
        String setLastDonationSQL = "UPDATE patient_medical_data " +
                "SET lastDonation = ? " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(setLastDonationSQL)) {
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getLastDonation(int patientID) {
        String lastDonationSQL = "SELECT lastDonation " +
                "FROM patient_medical_data " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(lastDonationSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("lastDonation");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Patient not found";
    }

    public static String getFirstReceive(int patientID) {
        String firstReceiveSQL = "SELECT firstReceive " +
                "FROM patient_medical_data " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(firstReceiveSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("firstReceive");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Patient not found";
    }

    public static boolean setFirstReceive(int patientID, String date) {
        String setFirstReceiveSQL = "UPDATE patient_medical_data " +
                "SET firstReceive = ? " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(setFirstReceiveSQL)) {
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setLastReceive(int patientID, String date) {
        String setLastReceiveSQL = "UPDATE patient_medical_data " +
                "SET lastReceive = ? " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(setLastReceiveSQL)) {
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, patientID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getLastReceive(int patientID) {
        String lastReceiveSQL = "SELECT lastReceive " +
                "FROM patient_medical_data " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(lastReceiveSQL)) {
            preparedStatement.setInt(1, patientID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("lastReceive");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Patient not found";
    }
}



