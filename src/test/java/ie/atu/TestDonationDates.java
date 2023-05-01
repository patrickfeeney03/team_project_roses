package ie.atu;

public class TestDonationDates {
    public static void main(String[] args) {
        /*
        System.out.println(PatientManager.getFirstDonation(2));

        System.out.println(PatientManager.setFirstDonation(2, "1990-01-01"));

        System.out.println(PatientManager.getFirstDonation(2));

        System.out.println("\n\nLogic to add donation dates to pmd table");
        int testPatientID = 9;
        String testDate = "2023-05-01";
        System.out.println(PatientManager.getFirstDonation(testPatientID));
        if (PatientManager.getFirstDonation(testPatientID) == "null") {
            PatientManager.setFirstDonation(testPatientID, testDate);
            PatientManager.setLastDonation(testPatientID, testDate);
        } else {
            PatientManager.setLastDonation(testPatientID, testDate);
        }
        System.out.println();

        System.out.println("The first donation for the patient: " + testPatientID +
                " was on " + PatientManager.getFirstDonation(testPatientID));
        System.out.println("The last donation for the patient: " + testPatientID +
                " was on " + PatientManager.getLastDonation(testPatientID));
         */

        // Receive
        int patientID = 3;
        System.out.println(PatientManager.getFirstReceive(patientID));
        System.out.println(PatientManager.setFirstReceive(patientID, "1999-03-30"));
        System.out.println(PatientManager.getFirstReceive(patientID));

    }
}
