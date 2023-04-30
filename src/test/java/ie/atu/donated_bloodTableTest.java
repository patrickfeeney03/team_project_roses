package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class donated_bloodTableTest {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }
    public static void main(String[] args) {
        /*
        TODO
        Create the following methods:
        public int getDonorBloodTypeID(donorID){}

        public string getDonorBloodTypeString(
         */
/*
        PatientManager patientManager = new PatientManager();
        BloodType bloodType = new BloodType("A", '+');
        // Get method to get the bloodType from the patient.
        Donor donor = new Donor(patientManager.getSinglePatientInfo(1), bloodType);

 */
        System.out.println(getDonorBloodTypeString(3));
    }

    public static String getDonorBloodTypeString(int donorID) {
        PatientManager patientManager = new PatientManager();
        String getBloodTypeSQL = "SELECT pi.patientFirstName, CONCAT(bt.blood_group, bt.rh_factor) AS blood_type " +
                "FROM patient_info pi " +
                "JOIN donor d ON pi.patientID = d.corresponding_patient_id " +
                "JOIN donated_blood db ON d.donor_unique_id = db.donorID_relation " +
                "JOIN blood_types bt ON db.blood_typesID = bt.id " +
                "WHERE d.donor_unique_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getBloodTypeSQL)) {
            preparedStatement.setInt(1, donorID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("blood_type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Nothing found";
    }
}
