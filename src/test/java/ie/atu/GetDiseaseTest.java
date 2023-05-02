package ie.atu;

public class GetDiseaseTest {

    public static void main(String[] args) {
        BloodType bloodType = new BloodType("A", '+');
        Donor donor = new Donor(1, "PatricK", "Feeney", 19,
                "2003-01-01", "myEmail", "ymAdres", "myhgone",
                "ermgPHone", bloodType);

        donor.setPatientDisease(PatientManager.getTableValues_patient_medical_data(donor.getPatient_Id()).get(1));
        System.out.println("donor holds: " + donor.getPatientDisease());
    }
}
