package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestAddBloodUnit {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("AB", '+');
        int unitsDonated = 2;
        BloodUnit bloodUnit = new BloodUnit(bloodType); // Holds date
        BloodBank bloodBank = new BloodBank(1, "EmailBank", "BankAddress",
                "bankPhone999");
        PatientManager patientManager = new PatientManager();
        Donor donor = new Donor(patientManager.getPatientByID(10), bloodType); // Patient already exists in DB
        Donation donation = new Donation(donor, bloodBank, bloodUnit, unitsDonated);

        // Figure out which blood_typeID corresponds to the bloodType object.
        String getBloodTypeID = "SELECT id FROM blood_types " +
                "WHERE blood_group = ? AND rh_factor = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getBloodTypeID)) {
            preparedStatement.setString(1, donation.getDonor().getBloodType().getBloodGroup());
            preparedStatement.setString(2, Character.toString
                    (donation.getDonor().getBloodType().getRhFactor()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                donation.getBloodUnit().setBloodIDSQL(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Patient's blood type: ");

        // Add unit to blood_units_date table
        String addIntoUnitsSQL =
                "INSERT INTO blood_units_date (blood_typesID, donorID, blood_date) " +
                        "VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addIntoUnitsSQL)) {
            preparedStatement.setInt(1, 2);
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
        boolean updateBlood_stock = BloodStockManager.updateTable_blood_stock();

        // Add the patient that just donated blood to the donor table.
        String addPatientToDonorTableSQL = "INSERT INTO DONOR (corresponding_patient_id) " +
                "VALUES (?)";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addPatientToDonorTableSQL)) {
            preparedStatement.setInt(1, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
