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

        // Donor is A+
        assertFalse(BloodType.isCompatible(APlus, OPlus));
        assertFalse(BloodType.isCompatible(APlus, OMinus));
        assertTrue(BloodType.isCompatible(APlus, APlus));
        assertFalse(BloodType.isCompatible(APlus, AMinus));
        assertFalse(BloodType.isCompatible(APlus, BPlus));
        assertFalse(BloodType.isCompatible(APlus, BMinus));
        assertTrue(BloodType.isCompatible(APlus, ABPlus));
        assertFalse(BloodType.isCompatible(APlus, ABMinus));
        // Donor is A-
        assertFalse(BloodType.isCompatible(AMinus, OPlus));
        assertFalse(BloodType.isCompatible(AMinus, OMinus));
        assertTrue(BloodType.isCompatible(AMinus, APlus));
        assertTrue(BloodType.isCompatible(AMinus, AMinus));
        assertFalse(BloodType.isCompatible(AMinus, BPlus));
        assertFalse(BloodType.isCompatible(AMinus, BMinus));
        assertTrue(BloodType.isCompatible(AMinus, ABPlus));
        assertTrue(BloodType.isCompatible(AMinus, ABMinus));

        // Donor is B+
        assertFalse(BloodType.isCompatible(BPlus, OPlus));
        assertFalse(BloodType.isCompatible(BPlus, OMinus));
        assertFalse(BloodType.isCompatible(BPlus, APlus));
        assertFalse(BloodType.isCompatible(BPlus, AMinus));
        assertTrue(BloodType.isCompatible(BPlus, BPlus));
        assertFalse(BloodType.isCompatible(BPlus, BMinus));
        assertTrue(BloodType.isCompatible(BPlus, ABPlus));
        assertFalse(BloodType.isCompatible(BPlus, ABMinus));
        // Donor is B-
        assertFalse(BloodType.isCompatible(BMinus, OPlus));
        assertFalse(BloodType.isCompatible(BMinus, OMinus));
        assertFalse(BloodType.isCompatible(BMinus, APlus));
        assertFalse(BloodType.isCompatible(BMinus, AMinus));
        assertTrue(BloodType.isCompatible(BMinus, BPlus));
        assertTrue(BloodType.isCompatible(BMinus, BMinus));
        assertTrue(BloodType.isCompatible(BMinus, ABPlus));
        assertTrue(BloodType.isCompatible(BMinus, ABMinus));

        // Donor is AB+
        assertFalse(BloodType.isCompatible(ABPlus, OPlus));
        assertFalse(BloodType.isCompatible(ABPlus, OMinus));
        assertFalse(BloodType.isCompatible(ABPlus, APlus));
        assertFalse(BloodType.isCompatible(ABPlus, AMinus));
        assertFalse(BloodType.isCompatible(ABPlus, BPlus));
        assertFalse(BloodType.isCompatible(ABPlus, BMinus));
        assertTrue(BloodType.isCompatible(ABPlus, ABPlus));
        assertFalse(BloodType.isCompatible(ABPlus, ABMinus));
        // Donor is AB-
        assertFalse(BloodType.isCompatible(ABMinus, OPlus));
        assertFalse(BloodType.isCompatible(ABMinus, OMinus));
        assertFalse(BloodType.isCompatible(ABMinus, APlus));
        assertFalse(BloodType.isCompatible(ABMinus, AMinus));
        assertFalse(BloodType.isCompatible(ABMinus, BPlus));
        assertFalse(BloodType.isCompatible(ABMinus, BMinus));
        assertTrue(BloodType.isCompatible(ABMinus, ABPlus));
        assertTrue(BloodType.isCompatible(ABMinus, ABMinus));
    }
}
