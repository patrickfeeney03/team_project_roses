package ie.atu;

import java.util.List;

public class BloodBankManagerTest {
    public static void main(String[] args) {
        // Select info from a bloodBank by using the ID
        BloodBank bloodBankInfo = BloodBankManager.getBloodBankByID(100);

        // Select info from all bloodBanks by using their ID
        List<BloodBank> bloodBanks = BloodBankManager.getAllBloodBanks();
    }
}
