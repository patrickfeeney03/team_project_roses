package ie.atu;

public class Donation {
    private int donationID;
    private Donor donor;
    private BloodType bloodType;
    private int unitsDonated;

    public Donation(int donationID, Donor donor,
                    BloodType bloodtype, int unitsDonated) {
        this.donationID = donationID;
        this.donor = donor;
        this.bloodType = bloodtype;
        this.unitsDonated = unitsDonated;
    }

    //getters and setters
    public int getDonationID() {
        return donationID;
    }

    public void setDonationID(int donationID) {
        this.donationID = donationID;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getUnitsDonated() {
        return unitsDonated;
    }

    public void setUnitsDonated(int unitsDonated) {
        this.unitsDonated = unitsDonated;
    }
}