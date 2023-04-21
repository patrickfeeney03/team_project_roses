package ie.atu;

public class PatientManagerTest {
    public static void main(String[] args) {
        // Create PatientManager object
        PatientManager PatientManager = new PatientManager();

        // Initialise properties in the Patient class
        Patient patientTest = new Patient(22, "some@email.com", "password03", "Sean",
                "Conroy", "someStreet09", "353 89 000 0000", "353 89 100 0000",
                55);

        // Test if addPatient was successful
        boolean addPatientResult = PatientManager.addPatient(patientTest);
        System.out.println("addUser result: " + addPatientResult);
    }
}
