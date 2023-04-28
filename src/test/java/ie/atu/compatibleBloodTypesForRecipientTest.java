package ie.atu;

import java.util.List;

public class compatibleBloodTypesForRecipientTest {
    public static void main(String[] args) {
        BloodManager bloodManager = new BloodManager();

        BloodType recipientBloodType = new BloodType("A", '-');
        List<String> compatibleBloodTypes = BloodManager.getCompatibleBloodTypes(recipientBloodType);
        System.out.println(compatibleBloodTypes);

        BloodType recipientBloodType2 = new BloodType("A", '+');
        List<String> compatibleBloodTypes2 = BloodManager.getCompatibleBloodTypes(recipientBloodType2);
        System.out.println(compatibleBloodTypes2);
    }
}
