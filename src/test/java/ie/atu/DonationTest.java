package ie.atu;

public class DonationTest {
    public static void main(String[] args) {
        // Use will input the info for populating the bloodType
        BloodType bloodType1 = new BloodType("A", '+');
        Donor donor1 = new Donor(0, "Patrick", "Feeney", 19,
                "15/12/2003", "emailpatrick", "patricksAddress", "999222212",
                "393298292", bloodType1);

        int donatedAmount1 = 3;

        Donation donation2  = new Donation(donor1, donatedAmount1);
        System.out.println("Some of Donor's info\n" +
                "Name: " + donor1.getPatient_firstName() +
                "\nAge: " + donor1.getPatient_age() +
                "\nFirst visit: " + donor1.getRegister_date());
        System.out.println("\nDonated amount: " + donatedAmount1 + " units.");
        System.out.println("Donated amount info from donation2: " + donation2.getUnitsDonated());
        //System.out.println("\nBloodType info: " + bloodType1.toString());
        System.out.println("BloodType info from donor1: " + donor1.getBloodType().toString());
        System.out.println("BloodType info from donation2: " + donation2.getDonor().getBloodType().toString());
    }
}
