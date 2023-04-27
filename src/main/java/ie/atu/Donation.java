package ie.atu;

public class Donation {
    private int donationID;
    private Donor donor;
    private BloodBank bloodBank;
    private int unitsDonated;

    public Donation(Donor donor, int unitsDonated) {
        this.donor = donor;
        this.unitsDonated = unitsDonated;
    }

    public Donation(Donor donor, BloodBank bloodBank, int unitsDonated) {
        this.donor = donor;
        this.bloodBank = bloodBank;
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

    public int getUnitsDonated() {
        return unitsDonated;
    }

    public void setUnitsDonated(int unitsDonated) {
        this.unitsDonated = unitsDonated;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}
