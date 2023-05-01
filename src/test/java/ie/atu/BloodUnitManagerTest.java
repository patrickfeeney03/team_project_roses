package ie.atu;

public class BloodUnitManagerTest {
    public static void main(String[] args) {

        // Method to select blood from the database depending on which blood is closer to expire
        BloodUnit BestBloodByDate = BloodUnitManager.getBestBloodByDate();

        // Method to select blood from the database depending on which blood type has the most amount
        BloodStock BestBloodByAmount = BloodUnitManager.getBestBloodByAmount();
    }
}
