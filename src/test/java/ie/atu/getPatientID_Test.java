package ie.atu;

public class getPatientID_Test {


    public static void main(String[] args) {
        Patient patient = new Patient("Samantha", "Smith", 28, "1995-07-04",
                "ssmith@example.com", "321 Second St, Anytown USA", "555-555-8765",
                "555-555-1234");

        int patientID = PatientManager.getPatientIDWithPatientObject(patient);
        System.out.println(patientID);
    }

}
