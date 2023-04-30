package ie.atu;

public class donated_bloodTableTest {
    public static void main(String[] args) {
        /*
        TODO
        Create the following methods:
        public int getDonorBloodTypeID(donorID){}

        public string getDonorBloodTypeString(
         */

        PatientManager patientManager = new PatientManager();
        BloodType bloodType = new BloodType("A", '+');
        // Get method to get the bloodType from the patient.
        Donor donor = new Donor(patientManager.getSinglePatientInfo(1), bloodType);
    }

    public static String getDonorBloodTypeString(int donorID) {
        PatientManager patientManager = new PatientManager();
        //BloodType bloodType = new BloodType("A", '+');
        //Patient patient = new Patient(patientManager.getSinglePatientInfo(donorID))


        return "test";
    }
}
