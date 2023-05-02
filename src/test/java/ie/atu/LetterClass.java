package ie.atu;

public class LetterClass {
    public static void Donor_Report(Donation donation){
        PatientManager patientManager = new PatientManager();
        System.out.println(donation.getDonor().getBloodType().getBloodGroup());
    }
    public static void Recipient_Report(Receive receive){
        PatientManager patientManager = new PatientManager();
        System.out.println(receive.getRecipient().getBloodType().getBloodGroup());
    }

    public static void main(String[] args) {
        int patientID = 1;
        BloodType bloodType = new BloodType("A", '+');
        int unitsDonated = 1;
        BloodUnit bloodUnit = new BloodUnit(bloodType);
        PatientManager patientManager = new PatientManager();
        Patient patient = patientManager.getSinglePatientInfo(patientID);
        Donor donor = new Donor(patient, bloodType);
        BloodBank bloodBank = new BloodBank(0, "bloodBank0 Email", "Galway",
                "1234131");
        Donation donation = new Donation(donor, bloodBank, bloodUnit, unitsDonated);
        System.out.println("Name: "+donation.getDonor().getPatient_firstName()+donation.getDonor().getPatient_lastName()+ donation.getDonor().getPatient_email()+donation.getDonor().getPatient_address()+donation.getDonor().getPatient_phone());
        //Report report = new Report();
        //report.Donor_Report(donation);
    }
}
