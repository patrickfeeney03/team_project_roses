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

        // By setting the ID to 0, the auto-increment from SQL will automatically set the ID.
        User newUser = new User(0, patient_email, patient_firstName, patient_lastName, patient_address,
                patient_phone, patient_emergencyPhone, patient_age);
        boolean wasRegistrationSuccessful = addPatient(newPatient);

        if (wasRegistrationSuccessful) {
            System.out.println("Patient's registration successful.");
        }
        else {
            System.out.println("Patient's registration failed.");
        }
    }
}
