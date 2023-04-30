package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodBankManager {

    // here there will be two methods
    // 1 getBloodBankByID
    // 2 printBloodBanks

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public static BloodBank getBloodBankByID(int bankID) {
        BloodBank bloodBank = null;
        String selectBankInfoSQ = "SELECT * FROM blood_bank "
                + "WHERE bankID = ?";

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectBankInfoSQ)) {
            preparedStatement.setInt(100, bankID);

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
}
