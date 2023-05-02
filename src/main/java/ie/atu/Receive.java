package ie.atu;

public class Receive {
    private int receiveID;
    private Recipient recipient;
    private BloodBank bloodBank;
    private int unitsReceived;
    private BloodUnit bloodUnit;

    public Receive(Recipient recipient, BloodBank bloodBank, int unitsReceived) {
        this.recipient = recipient;
        this.bloodBank = bloodBank;
        this.unitsReceived = unitsReceived;
    }

    public Receive(Recipient recipient, BloodBank bloodBank, int unitsReceived, BloodUnit bloodUnit) {
        this.recipient = recipient;
        this.bloodBank = bloodBank;
        this.unitsReceived = unitsReceived;
        this.bloodUnit = bloodUnit;
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

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public BloodUnit getBloodUnit() {
        return bloodUnit;
    }

    public void setBloodUnit(BloodUnit bloodUnit) {
        this.bloodUnit = bloodUnit;
    }
}
