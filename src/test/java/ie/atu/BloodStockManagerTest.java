package ie.atu;

public class BloodStockManagerTest {
    public static void main(String[] args) {
        BloodStockManager myBloodManager = new BloodStockManager();
        BloodType exampleType = new BloodType("A", '+');

        int amount = myBloodManager.getAvailableBloodstock(exampleType);
        System.out.println("The amount of blood is: " + amount);

    }
}
