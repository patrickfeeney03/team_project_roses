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

        // Donor is O+
        assertTrue(BloodType.isCompatible(OPlus, OPlus));
        assertFalse(BloodType.isCompatible(OPlus, OMinus));
        assertTrue(BloodType.isCompatible(OPlus, APlus));
        assertFalse(BloodType.isCompatible(OPlus, AMinus));
        assertTrue(BloodType.isCompatible(OPlus, BPlus));
        assertFalse(BloodType.isCompatible(OPlus, BMinus));
        assertTrue(BloodType.isCompatible(OPlus, ABPlus));
        assertFalse(BloodType.isCompatible(OPlus, ABMinus));
        // Donor is O-
        assertTrue(BloodType.isCompatible(OMinus, OPlus));
        assertTrue(BloodType.isCompatible(OMinus, OMinus));
        assertTrue(BloodType.isCompatible(OMinus, APlus));
        assertTrue(BloodType.isCompatible(OMinus, AMinus));
        assertTrue(BloodType.isCompatible(OMinus, BPlus));
        assertTrue(BloodType.isCompatible(OMinus, BMinus));
        assertTrue(BloodType.isCompatible(OMinus, ABPlus));
        assertTrue(BloodType.isCompatible(OMinus, ABMinus));

        // Donor is A

        // Donor is B


        // Donor is AB
    }
}
