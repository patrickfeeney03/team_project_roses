package ie.atu;

public class PatientManagerTest {
    public static void main(String[] args) {
        // Create PatientManager object
        PatientManager patientManager = new PatientManager();

        // Initialise properties in the Patient class
        Patient patientTest = new Patient(100, "Sean", "Conroy", 55,
                "1987-03-06", "another@gmail.com", "anotherAddress99",
                "353 67 555 5555", "353 444 4444");

        // Test if addPatient was successful
        boolean addPatientResult = patientManager.addPatient(patientTest);
        System.out.println("addPatient result: " + addPatientResult);

        // Test if removePatient was successful
        boolean removePatientResult = patientManager.removePatient(patientTest);
        System.out.println("removePatient result: " + removePatientResult);

        // Test for updateUser()
        patientTest.setPatient_age(24);
        patientTest.setPatient_email("notGmail@gmail.com");
        boolean updateResult = PatientManager.updatePatient(patientTest);
        System.out.println("updateUser result: " + updateUserResult);
    }
}
