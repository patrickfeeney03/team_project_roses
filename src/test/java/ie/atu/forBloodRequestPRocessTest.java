package ie.atu;

import java.util.ArrayList;
import java.util.List;

public class forBloodRequestPRocessTest {
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("AB", '+');
        System.out.println(BloodManager.getCompatibleBloodTypes(bloodType));
        System.out.println(BloodManager.getCompatibleBloodTypes(bloodType).size());
        List<String> myList = BloodManager.getCompatibleBloodTypes(bloodType);
        System.out.println(myList);
        //System.out.println(BloodUnitManager.getBestBloodByDate(myList));
        System.out.println();

        // Create BloodUnit. There is a possibility that there is no options for a type.
        List<BloodUnit> bloodUnitsList = new ArrayList<>();

        for (int i = 0; i < myList.size(); i++) {
            BloodUnit bloodUnit = new BloodUnit();
            BloodType bloodType2 = new BloodType();
            bloodType2.setBloodGroup(
                    myList.get(i).substring(0, myList.get(i).length() - 1)
            );
            bloodType2.setRhFactor(
                    myList.get(i).charAt(myList.get(i).length() - 1)
            );
            bloodUnit.setBloodType(bloodType2);
            bloodUnit.setBloodIDSQL(BloodUnitManager.getBestBloodByDate(myList.get(i)));
            System.out.println("ID of unit n" + i + " " + bloodUnit.getBloodIDSQL());
            bloodUnitsList.add(bloodUnit);
        }
        for (BloodUnit bloodUnit : bloodUnitsList) {
            System.out.println("BloodUnit unique id: " + bloodUnit.getBloodIDSQL() +
            ", BloodUnit type; " + bloodUnit.getBloodType().toString());
        }
    }
}
