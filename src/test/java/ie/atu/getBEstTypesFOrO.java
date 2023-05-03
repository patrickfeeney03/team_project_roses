package ie.atu;

import java.util.ArrayList;
import java.util.List;

public class getBEstTypesFOrO {
    public static void main(String[] args) {
        //System.out.println(BloodUnitManager.getBestBloodByDateList20("O-"));
        //System.out.println(BloodUnitManager.getBestBloodByDateList20("O-"));
        List<Integer> myList = new ArrayList<>();
        myList = BloodUnitManager.getBestBloodByDateList20("O-");

        List<Integer> anotherList = new ArrayList<>();
        for(Integer int1 : myList) {
            anotherList.add(int1);
        }

        System.out.println(anotherList);

    }
}
