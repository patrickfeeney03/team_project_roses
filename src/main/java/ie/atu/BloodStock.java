package ie.atu;

public class BloodStock {
    private BloodType bloodGroup;
    private int amount;

    public BloodStock(BloodType bloodGroup, int amount) {
        this.bloodGroup = bloodGroup;
        this.amount = amount;
    }

    public BloodStock() {
    }

    public BloodType getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodType bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

