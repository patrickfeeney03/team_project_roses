package ie.atu;

import java.sql.Connection;
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
    }
}
