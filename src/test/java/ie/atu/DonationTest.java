package ie.atu;

public class DonationTest {
    public static void main(String[] args) {
        // Use will input the info for populating the bloodType
        BloodType bloodType1 = new BloodType("A", '+');
        int donatedAmount1 = 3;
        Donation donation1 = new Donation(bloodType1, donatedAmount1);
        System.out.println("Units donated: " + donation1.getUnitsDonated());
        System.out.println("Last visit to hospital: " + donation1.getDonor().getLast_Med());

    }


}
