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
        BloodType bloodTypeAMinus = new BloodType("A", '-');
        BloodType bloodTypeBPlus = new BloodType("B", '+');
        BloodType bloodTypeBMinus = new BloodType("B", '-');
        BloodType bloodTypeABPlus = new BloodType("AB", '+');
        BloodType bloodTypeABMinus = new BloodType("AB", '-');
        BloodType bloodTypeOPlus = new BloodType("O", '+');
        BloodType bloodTypeOMinus = new BloodType("O", '-');
        // Donor - Recipient

        // Donor is A


        // Donor is B

        // Donor is O

        // Donor is AB
    }
}
