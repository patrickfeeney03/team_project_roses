package ie.atu;

public class Donor extends Patient {
    private String bloodType;
    private String Last_Med;
    private String First_Med;
    public Donor(int patient_Id, String patient_firstName, String patient_lastName, int patient_age, String patient_DOB, String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone, String First_Med, String Last_Med, String bloodType) {
        super(patient_Id, patient_firstName, patient_lastName, patient_age, patient_DOB, patient_email, patient_address, patient_phone, patient_emergencyPhone);
        this.First_Med = First_Med;
        this.Last_Med = Last_Med;
        this.bloodType = bloodType;
    }

    public Donor(String bloodType) {
        this.bloodType = bloodType;
        this.Last_Med = "Yesterday";
        this.First_Med = "A year ago";
    }



    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getLast_Med() {
        return Last_Med;
    }

    public void setLast_Med(String Last_Med) {
        this.Last_Med = Last_Med;
    }
    public String getFirst_Med() {
        return First_Med;
    }

    public void setFirst_Med(String First_Med) {
        this.First_Med = First_Med;
    }



}
