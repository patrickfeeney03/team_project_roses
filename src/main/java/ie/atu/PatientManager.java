package ie.atu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientManager {

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public void register(Scanner scanner) {
        System.out.print("Enter patient email: ");
        String patient_email = scanner.next();
        System.out.print("Enter patient's first name: ");
        String patient_firstName = scanner.next();
        System.out.print("Enter patient's last name: ");
        String patient_lastName = scanner.next();
        System.out.print("Enter patient's address: ");
        String patient_address = scanner.next();
        System.out.print("Enter patient's phone: ");
        String patient_phone = scanner.next();
        System.out.print("Enter patient's emergency phone: ");
        String patient_emergencyPhone = scanner.next();
        System.out.print("Enter patient's age: ");
        int patient_age = scanner.nextInt();
    }
}
