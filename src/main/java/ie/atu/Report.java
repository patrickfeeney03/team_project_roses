package ie.atu;

public class Report {

    public void Donor_Report(Donation donation){
        PatientManager patientManager = new PatientManager();
        System.out.println("******************************************************************\n\n\n\n\n\n\n\n\n");
        System.out.println("Donor Report:");
        System.out.println("Name: "+donation.getDonor().getPatient_firstName()+donation.getDonor().getPatient_lastName()+"\nD.O.B: "
                +donation.getDonor().getPatient_DOB()+"\nEmail: "+donation.getDonor().getPatient_email()+"\nAddress: "
                +donation.getDonor().getPatient_address()+"\nPhone: "+donation.getDonor().getPatient_phone()+"\nBlood type: "+donation.getDonor().getBloodType()+"\nUnits Donated: "
                +donation.getUnitsDonated());
        System.out.println("\n\n\n\n\n\n\n\n\n******************************************************************");
    }
    public void Recipient_Report(Receive receive){
        PatientManager patientManager = new PatientManager();
        System.out.println("******************************************************************\n\n\n\n\n\n\n\n\n");
        System.out.println("Recipient Report:");
        System.out.println("Name: "+receive.getRecipient().getPatient_firstName()+receive.getRecipient().getPatient_lastName()+
                "\nD.O.B: "+receive.getRecipient().getPatient_DOB()+"\nEmail: "+receive.getRecipient().getPatient_email()+"\nAddress: "
                +receive.getRecipient().getPatient_address()+"\nPhone: "+receive.getRecipient().getPatient_phone()+"\nBlood type: "+receive.getRecipient().getBloodType()+"\nUnits Received: "
                +receive.getUnitsReceived());
        System.out.println("\n\n\n\n\n\n\n\n\n******************************************************************");
    }
}
