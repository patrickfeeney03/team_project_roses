package ie.atu;

public class BloodType {
    private String bloodGroup; // A B AB or O
    private char rhFactor; // the rhFactor can be + or -

    public BloodType(String bloodGroup, char rhFactor) {
        this.bloodGroup = bloodGroup;
        this.rhFactor = rhFactor;
    }

    // Getter sand setters...

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public char getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(char rhFactor) {
        this.rhFactor = rhFactor;
    }

    // Return type of blood as text

    @Override
    public String toString() {
        return bloodGroup + rhFactor; // Ex: A+
    }

    public static boolean isCompatible(BloodType donor, BloodType recipient) {
        // This checks for rH compatibility
        if (donor.getRhFactor() == '-' && recipient.getRhFactor() == '+') {
            return false;
        }

        // This checks for blood group compatibility
        switch (donor.getBloodGroup()) {
            case "O":
                // O group can donate to any group
                return true;
        }
    return false;
    }
}
