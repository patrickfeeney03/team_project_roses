package ie.atu;

import java.util.List;

public class forBloodRequestPRocessTest {
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("A", '-');
        System.out.println(BloodManager.getCompatibleBloodTypes(bloodType));
        System.out.println(BloodManager.getCompatibleBloodTypes(bloodType).size());
        List<String> myList = BloodManager.getCompatibleBloodTypes(bloodType);
        System.out.println(myList);
        System.out.println(BloodUnitManager.getBestBloodByDate(myList));
        System.out.println();
    }
}
