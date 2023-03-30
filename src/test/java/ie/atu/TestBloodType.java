package ie.atu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBloodType {
    @Test
    void testToString() {
        BloodType bloodTypeAMinus = new BloodType("A", '-');
        assertEquals("A-", bloodTypeAMinus.toString());
        BloodType bloodTypeOPlus = new BloodType("O", '+');
        assertEquals("O+", bloodTypeOPlus.toString());
        BloodType bloodTypeABPlus = new BloodType("AB", '+');
        assertEquals("AB+", bloodTypeABPlus.toString());
    }

    @Test
    void testIsCompatible() {
        BloodType bloodTypeAPlus = new BloodType("A", '+');
        BloodType bloodTypeBMinus = new BloodType("B", '-');
        BloodType bloodTypeOPlus = new BloodType("O", '+');

        //                                  Donor - Recipient
        assertFalse(BloodType.isCompatible(bloodTypeAPlus, bloodTypeBMinus));
        // B- can't receive from A+ so assertFalse is successful.


    }
}
