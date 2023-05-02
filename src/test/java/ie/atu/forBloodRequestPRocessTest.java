package ie.atu;

import java.util.ArrayList;
import java.util.List;

public class forBloodRequestPRocessTest {
    public static void main(String[] args) {
        BloodType bloodType = new BloodType("O", '-');
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


        BloodUnit randomComptibleUnit = BloodUnitManager.getRandomValidUnit(bloodUnitsList);
        System.out.println("Unique ID: " + randomComptibleUnit.getBloodIDSQL() +
                " BloodType: " + randomComptibleUnit.getBloodType().toString());
        BloodUnit randomComptibleUnit2 = BloodUnitManager.getRandomValidUnit(bloodUnitsList);
        System.out.println("Unique ID: " + randomComptibleUnit2.getBloodIDSQL() +
                " BloodType: " + randomComptibleUnit2.getBloodType().toString());
        BloodUnit randomComptibleUnit3 = BloodUnitManager.getRandomValidUnit(bloodUnitsList);
        System.out.println("Unique ID: " + randomComptibleUnit3.getBloodIDSQL() +
                " BloodType: " + randomComptibleUnit3.getBloodType().toString());
        BloodUnit randomComptibleUnit4 = BloodUnitManager.getRandomValidUnit(bloodUnitsList);
        System.out.println("Unique ID: " + randomComptibleUnit4.getBloodIDSQL() +
                " BloodType: " + randomComptibleUnit4.getBloodType().toString());


    }
}
