package ie.atu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodBankManager {

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public static BloodBank getBloodBankByID(int bankID) {
        BloodBank bloodBank = null;
        String selectBankInfoSQ = "SELECT * FROM blood_bank "
                + "WHERE bankID = ?";

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBankInfoSQ)) {
            preparedStatement.setInt(1, bankID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                bloodBank = new BloodBank();
                bloodBank.setBankID(resultSet.getInt("bankID"));
                bloodBank.setBankEmail(resultSet.getString("bankEmail"));
                bloodBank.setBankAddress(resultSet.getString("bankAddress"));
                bloodBank.setBankPhone(resultSet.getString("bankPhone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodBank;
    }

   public static List<BloodBank> getAllBloodBankByID() {
        List<BloodBank> bloodBanks = new ArrayList<>();
        String selectAllBankInfoSQ = "SELECT * FROM blood_bank ";

        try (Connection connection = DBConnectionUtils.getConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(selectAllBankInfoSQ)) {

            while(resultSet.next()) {
                BloodBank bloodBank = new BloodBank();
                bloodBank.setBankID(resultSet.getInt("bankID"));
                bloodBank.setBankEmail(resultSet.getString("bankEmail"));
                bloodBank.setBankAddress(resultSet.getString("bankAddress"));
                bloodBank.setBankPhone(resultSet.getString("bankPhone"));
                bloodBanks.add(bloodBank);

                System.out.println("bankID: " + bloodBank.getBankID());
                System.out.println("bankEmail: " + bloodBank.getBankEmail());
                System.out.println("bankAddress: " + bloodBank.getBankAddress());
                System.out.println("bankPhone: " + bloodBank.getBankPhone());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodBanks;
    }
}
