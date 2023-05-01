package ie.atu;

public class Report {


    public void Donor_Report(){

        PatientManager patientManager = new PatientManager();
        BloodType bloodType = new BloodType("A",'+');
        Donor donor = new Donor(patientManager.getSinglePatientInfo(2),bloodType);
        BloodBank bloodBank = new BloodBank(3,"dsa","sad","dsa");
        Donation donation = new Donation(donor,bloodBank,5);

        System.out.println(donation.getDonor().getBloodType().getBloodGroup());


    }
    public void Recipient_Report(){


        PatientManager patientManager = new PatientManager();
        BloodType bloodType = new BloodType("A",'+');
        Recipient recipient = new Recipient(patientManager.getSinglePatientInfo(2),bloodType);
        BloodBank bloodBank = new BloodBank(3,"dsa","sad","dsa");
        Receive receive = new Receive(recipient,bloodBank,6);


    }
}
