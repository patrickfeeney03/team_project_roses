package ie.atu;

import java.util.Scanner;

/*public class BloodStockManagerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BloodManager myBloodManager = new BloodManager();
        BloodType exampleType = new BloodType("A", '+');

        // Test for getAvailableBloodStock()
        int amount = myBloodManager.getAvailableBloodstock(exampleType);
        System.out.println("The amount of blood is: " + amount);

        System.out.println("Get available blood of what group: ");
        String bloodGroup = scanner.next();
        System.out.println("Rh factor: ");
        char rhFactor = scanner.next().charAt(0);
        BloodType exampleType2 = new BloodType(bloodGroup, rhFactor);
        int amount2 = myBloodManager.getAvailableBloodstock(exampleType2);
        System.out.println("The amount second amount of blood is: " + amount2);

        // Test for updateBloodStock()
        boolean updateSuccessful = myBloodManager.updateBloodStock(exampleType, 20);
        System.out.println("Update successful: " + updateSuccessful);
        System.out.println(myBloodManager.getAvailableBloodstock(exampleType));

        // Test for requestBlood()
        BloodType abMinusType = new BloodType("A", '+');
        boolean requestSuccessful = myBloodManager.requestBlood(abMinusType, 5);
        System.out.println("Request successful: " + requestSuccessful);

        // Test for recordDonation()
        BloodType aPlusType = new BloodType("A", '+');
        boolean donationSuccessful = myBloodManager.recordDonation(aPlusType, 20);
        System.out.println("Donation successful: " + donationSuccessful);

        // Test for getStock()
        /*List<BloodStock> bloodStockList = myBloodManager.getStock();
        for (BloodStock stock : bloodStockList) {
            System.out.println("Blood Type: " + stock.getBloodGroup() + ", Amount: " + stock.getAmount());
        }
    }
}*/
