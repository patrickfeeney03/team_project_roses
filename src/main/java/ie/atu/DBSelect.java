package ie.atu;

public class DBSelect {
    public static void main(String[] args) {
        String selectSQL = "SELECT u.patientFirstName, u.patientLastName, e.patientDisease " +
                "FROM patient_info u " +
                "JOIN patient_medical_data e ON u.patientID = e.patientID";

    }
}
