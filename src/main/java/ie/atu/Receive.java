package ie.atu;

public class Receive {
    private Recipient recipient;
    private BloodBank bloodBank;
    private int unitsReceived;

    public Receive(Recipient recipient, BloodBank bloodBank, int unitsReceived) {
        this.recipient = recipient;
        this.bloodBank = bloodBank;
        this.unitsReceived = unitsReceived;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }

    public int getUnitsReceived() {
        return unitsReceived;
    }

    public void setUnitsReceived(int unitsReceived) {
        this.unitsReceived = unitsReceived;
    }
}
