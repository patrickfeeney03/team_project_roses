package ie.atu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelect {
    public static void main(String[] args) {
        String selectSQL = "SELECT u.patientFirstName, u.patientLastName, e.patientDisease " +
                "FROM patient_info u " +
                "JOIN patient_medical_data e ON u.patientID = e.patientID";

        try (Connection connection = DBConnectionUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String patientFirstName = resultSet.getString("patientFirstName");
                String patientLastName = resultSet.getString("patientLastName");
                String patientDisease = resultSet.getString("patientDisease");

                System.out.println("Username: " + patientFirstName + ", Password: " + patientLastName + ", Email: " + patientDisease);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
