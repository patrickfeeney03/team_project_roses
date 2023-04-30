package ie.atu;

public class BloodBank{
    private int bankID;
    private String bankEmail;
    private String bankAddress;
    private String bankPhone;

    public BloodBank(int bankID, String bankEmail, String bankAddress, String bankPhone) {
        this.bankID = bankID;
        this.bankEmail = bankEmail;
        this.bankAddress = bankAddress;
        this.bankPhone = bankPhone;
    }

    public BloodBank() {
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankEmail() {
        return bankEmail;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    @Override
    public String toString() {
        return "BloodBank values:\n" +
                "bankID: " + bankID +
                ", bankEmail: " + bankEmail +
                ", bankAddress: " + bankAddress +
                ", bankPhone=: " + bankPhone;
    }


}
