package ie.atu;

public class PatientManagerTest {
    public static void main(String[] args) {
        // Create PatientManager object
        PatientManager patientManager = new PatientManager();

        // Initialise properties in the Patient class
        Patient patientTest = new Patient(100, "Sean", "Conroy", 55,
                "1987-03-06", "another@gmail.com", "anotherAddress99",
                "353 67 555 5555", "353 44 444 7777");

        // Test if addPatient was successful
        boolean addPatientResult = patientManager.addPatient(patientTest);
        System.out.println("addPatient result: " + addPatientResult);

        // Test for updatePatient()
        patientTest.setPatient_age(24);
        patientTest.setPatient_email("notGmail@gmail.com");
        boolean updatePatientResult = patientManager.updatePatient(patientTest);
        System.out.println("updatePatient result: " + updatePatientResult);

        // Test for removePatient()
        boolean removePatientResult = patientManager.removePatient(patientTest);
        System.out.println("removePatient result: " + removePatientResult);

        // Test for getPatientByID() (for the patient_info table)
        Patient patientResult = patientManager.getPatientByID(5);
        System.out.println("getPatientByID result (id): " + patientResult.getPatient_Id());

        // Select all info of the patient getPatientInfo()
        Patient patientInfo = patientManager.getPatientInfo();

        // Select all info from a patient getSinglePatientInfo() (patient_info and patient_medical_data)
        Patient singlePatientInfo = patientManager.getSinglePatientInfo(7);
    }
}
