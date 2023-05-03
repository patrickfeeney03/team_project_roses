package ie.atu;

public class Donor extends Patient implements Person{
    private BloodType bloodType;

    public Donor(int patient_Id, String patient_firstName, String patient_lastName, int patient_age, String patient_DOB,
                 String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone,
                 BloodType bloodType) {
        super(patient_Id, patient_firstName, patient_lastName, patient_age, patient_DOB, patient_email, patient_address,
                patient_phone, patient_emergencyPhone);
        this.bloodType = bloodType;
    }

    public Donor(Patient patient, BloodType bloodType) {
        super(patient.getPatient_Id(), patient.getPatient_firstName(), patient.getPatient_lastName(),
                patient.getPatient_age(), patient.getPatient_DOB(), patient.getPatient_email(),
                patient.getPatient_address(), patient.getPatient_phone(), patient.getPatient_emergencyPhone());
        this.bloodType = bloodType;
    }

    public Donor() {
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "bloodType=" + bloodType +
                "} " + super.toString();
    }
}
