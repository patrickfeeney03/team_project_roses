package ie.atu;

public class BloodRequest {
    private int requestID;
    private Recipient recipient;
    private BloodType bloodType;
    private int unitsRequested;

    public BloodRequest( int requestID, Recipient recipient, BloodType bloodType, int unitsRequested) {
        this.requestID = requestID;
        this.recipient = recipient;
        this.bloodType = bloodType;
        this.unitsRequested = unitsRequested;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getUnitsRequested() {
        return unitsRequested;
    }

    public void setUnitsRequested(int unitsRequested) {
        this.unitsRequested = unitsRequested;
    }
}
