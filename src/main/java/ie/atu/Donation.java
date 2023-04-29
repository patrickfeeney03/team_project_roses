package ie.atu;

public class Donation {
    private int donationID;
    private Donor donor;
    private BloodBank bloodBank;
    private BloodUnit bloodUnit;
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

    public Donation(int donationID, Donor donor, BloodBank bloodBank, BloodUnit bloodUnit, int unitsDonated) {
        this.donationID = donationID;
        this.donor = donor;
        this.bloodBank = bloodBank;
        this.bloodUnit = bloodUnit;
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

    public BloodUnit getBloodUnit() {
        return bloodUnit;
    }

    public void setBloodUnit(BloodUnit bloodUnit) {
        this.bloodUnit = bloodUnit;
    }
}
