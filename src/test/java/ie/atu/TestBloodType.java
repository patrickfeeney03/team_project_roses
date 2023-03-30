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

        // B- --> A+
        assertFalse(BloodType.isCompatible(bloodTypeAPlus, bloodTypeBMinus));
        // O+ --> B+
        assertTrue(BloodType.isCompatible(bloodTypeOPlus, bloodTypeBPlus));
        // AB- --> O-
        assertFalse(BloodType.isCompatible(bloodTypeABMinus, bloodTypeOMinus));
        // A- --> AB+
        assertTrue(BloodType.isCompatible(bloodTypeAMinus, bloodTypeABPlus));
        // A- --> O+
        assertFalse(BloodType.isCompatible(bloodTypeAMinus, bloodTypeOPlus));
    }
}
