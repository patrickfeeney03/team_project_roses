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
        BloodType APlus = new BloodType("A", '+');
        BloodType AMinus = new BloodType("A", '-');
        BloodType BPlus = new BloodType("B", '+');
        BloodType BMinus = new BloodType("B", '-');
        BloodType ABPlus = new BloodType("AB", '+');
        BloodType ABMinus = new BloodType("AB", '-');
        BloodType OPlus = new BloodType("O", '+');
        BloodType OMinus = new BloodType("O", '-');
        // Donor - Recipient

        // Donor is A
        assertTrue(BloodType.isCompatible(AMinus, AMinus));


        // Donor is B

        // Donor is O

        // Donor is AB
    }
}
