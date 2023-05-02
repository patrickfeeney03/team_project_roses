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
        String updateBlood_stock = "UPDATE blood_stock bs " +
                "LEFT JOIN ( " +
                "SELECT blood_typesID, COUNT(*) AS count_units " +
                "FROM blood_units_date " +
                "GROUP BY blood_typesID " +
                ") bud ON bs.blood_type_id = bud.blood_typesID " +
                "SET bs.amount = COALESCE(bud.count_units, 0) " +
                "WHERE bs.blood_type_id BETWEEN 1 AND 8";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateBlood_stock)) {

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
