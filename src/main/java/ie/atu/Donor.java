package ie.atu;

public class Donor extends Patient {
    private BloodType bloodType;

    public Donor(int patient_Id, String patient_firstName, String patient_lastName, int patient_age, String patient_DOB,
                 String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone,
                 BloodType bloodType) {
        super(patient_Id, patient_firstName, patient_lastName, patient_age, patient_DOB, patient_email, patient_address,
                patient_phone, patient_emergencyPhone);
        this.bloodType = bloodType;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
