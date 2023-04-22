package ie.atu;

public class PatientManagerTest {
    public static void main(String[] args) {
        // Create PatientManager object
        PatientManager PatientManager = new PatientManager();

        // Initialise properties in the Patient class
        Patient patientTest = new Patient(55, "Sean", "Conroy", 55,
                "1987-03-06", "another@gmail.com", "anotherAddress99",
                "353 67 555 5555", "353 444 4444");

        // Test if addPatient was successful
        boolean addPatientResult = PatientManager.addPatient(patientTest);
        System.out.println("addPatient result: " + addPatientResult);

        boolean removePatientResult = PatientManager.removePatient(patientTest);
        System.out.println("removePatient result: " + removePatientResult);
    }
}
