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
}
