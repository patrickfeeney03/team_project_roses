package ie.atu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBloodType {
    @Test
    void testToString() {
        BloodType bloodType = new BloodType("A", '+');
        assertEquals("A+", bloodType.toString());
    }



    BloodType bloodTypeA = new BloodType("A", '+');
    BloodType bloodTypeB = new BloodType("B", '+');
    BloodType bloodTypeO = new BloodType("O", '-');

}
