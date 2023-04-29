package ie.atu;

public class TestAddBloodUnit {
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("A", '+');
        PatientManager patientManager = new PatientManager();

        // Get patient using their ID:
        Patient patient = patientManager.getPatientByID(2);
        System.out.println(patient);
    }

}
