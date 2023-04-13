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


}
