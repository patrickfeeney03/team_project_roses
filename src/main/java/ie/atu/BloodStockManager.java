package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BloodStockManager {
    public static Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public static boolean updateTable_blood_stock() {
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
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
