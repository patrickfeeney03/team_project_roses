package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BloodManager {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public boolean requestBlood(BloodType requestedBloodType, int amount) {
        int availableAmount = getAvailableBloodstock(requestedBloodType);

        if (amount <= availableAmount) {
            updateBloodStock(requestedBloodType, -amount);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean recordDonation(BloodType donatedBloodType, int amount) {
        int existingBloodStock = getAvailableBloodstock(donatedBloodType);

        if (amount <= 0) {
            return false;
        }
        return updateBloodStock(donatedBloodType, amount);
    }

    public List<BloodStock> getStock() {
        List<BloodStock> bloodStockList = new ArrayList<>();

        String selectSQL =
                "SELECT bs.amount, bt.blood_group, bt.rh_factor " +
                "FROM blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id";

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                String bloodGroup = resultSet.getString("blood_group");
                char rhFactor = resultSet.getString("rh_factor").charAt(0);
                BloodType bloodType = new BloodType(bloodGroup, rhFactor);
                BloodStock bloodStock = new BloodStock(bloodType, amount);
                bloodStockList.add(bloodStock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodStockList;
    }

    public int getAvailableBloodstock(BloodType bloodTypeAvailability) {
        int availableAmount = 0;
        String selectSQL =
                "SELECT bs.amount FROM blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id " +
                "WHERE bt.blood_group = ? AND bt.rh_factor = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, bloodTypeAvailability.getBloodGroup());
            preparedStatement.setString(2, Character.toString(bloodTypeAvailability.getRhFactor()));

            ResultSet resultSet = preparedStatement.executeQuery();

            if ( resultSet.next()) {
                availableAmount = resultSet.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableAmount;
    }

    public boolean updateBloodStock(BloodType bloodTypeToUpdate, int amount) {
        boolean updateSuccessful = false;
        String updateSQL =
                "UPDATE blood_stock bs " +
                "JOIN blood_types bt ON bs.blood_type_id = bt.id " +
                "SET bs.amount = bs.amount + ? " +
                "WHERE bt.blood_group = ? AND bt.rh_factor = ?";
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, bloodTypeToUpdate.getBloodGroup());
            preparedStatement.setString(3, Character.toString(bloodTypeToUpdate.getRhFactor()));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                updateSuccessful = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateSuccessful;
    }

    public static List<String> getCompatibleBloodTypes(BloodType recipientBloodType) {
        String[] bloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        List<String> compatibleBloodTypesForRecipient = new ArrayList<>();

        for (String bloodTypeString : bloodTypes) {
            BloodType testAgainstBloodType = new BloodType( ( bloodTypeString.substring(0, bloodTypeString.length() - 1) ),
                    ( bloodTypeString.charAt(bloodTypeString.length() - 1) ) );
            if (BloodType.isCompatible(testAgainstBloodType, recipientBloodType)) {
                compatibleBloodTypesForRecipient.add(testAgainstBloodType.toString());
            }
        }
        return compatibleBloodTypesForRecipient;
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
        return "";
    }

    public static boolean addBloodToDonated_blood(Donation donation) {
        // Loop?
        int unitsDonated = donation.getUnitsDonated();
        int rowsAffected = 0;
        for (int i = 0; i < unitsDonated; i++) {
            String insertTo_donated_blood = "INSERT INTO donated_blood (blood_typesID, donation_date, donorID_relation," +
                    "blood_bankID_relation) " +
                    "VALUES (?, ?, ?, ?)";

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertTo_donated_blood)) {
                preparedStatement.setInt(1, get_blood_typeID(donation.getDonor().getBloodType().toString()));
                preparedStatement.setString(2, donation.getBloodUnit().getDate());
                preparedStatement.setInt(3, PatientManager.getDonorIDFromPatientID(
                        donation.getDonor().getPatient_Id()));
                preparedStatement.setInt(4, 3);

                if (preparedStatement.executeUpdate() > 0) {
                    rowsAffected++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rowsAffected > 0) {
            System.out.println("Rows Affected: " + rowsAffected);
            return true;
        }
        return false;
    }

    public static int get_blood_typeID(String bloodType) {
        String selectBloodTypeID = "SELECT id " +
                "FROM blood_types " +
                "WHERE blood_group = ? AND rh_factor = ?";

        String group = bloodType.substring(0, bloodType.length() - 1);
        char rh_factor = bloodType.charAt(bloodType.length() - 1);

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectBloodTypeID)) {
            preparedStatement.setString(1, group);
            preparedStatement.setString(2, Character.toString(rh_factor));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   public static boolean set_BloodType_ID (int patientID,String bloodType){
        String setBloodTypeSQL = "UPDATE patient_medical_data " +
                "SET bloodTypeID = ?" +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(setBloodTypeSQL)) {
            preparedStatement.setString(1, bloodType );
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

    public static int get_BloodType_ID_From_PMD(int patientID){
        String getBloodTypeSQL = "SELECT bloodTypeID " +
                "FROM patient_medical_data " +
                "WHERE patientID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getBloodTypeSQL)){
            preparedStatement.setInt(1,patientID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return  resultSet.getInt("BloodTypeID");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  0;

    }

    public static BloodType getBloodTypeByID(int id) {
        String selectType = "SELECT blood_group, rh_factor FROM blood_types " +
                "WHERE id = ?";

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectType)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                BloodType bloodType = new BloodType(resultSet.getString("blood_group"),
                        resultSet.getString("rh_factor").charAt(0));
                return bloodType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
