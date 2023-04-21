package ie.atu;

public class Patient {
    private int patient_Id;
    private String patient_email;
    private String patient_password;
    private String patient_firstName;
    private String patient_lastName;
    private String patient_address;
    private String patient_phone;
    private String patient_emergencyPhone;
    private int patient_age;

    public Patient(int patient_Id, String patient_email, String patient_password, String patient_firstName, String patient_lastName,
                   String patient_address, String patient_phone, String patient_emergencyPhone, int patient_age) {
        this.patient_Id = patient_Id;
        this.patient_email = patient_email;
        this.patient_password = patient_password;
        this.patient_firstName = patient_firstName;
        this.patient_lastName = patient_lastName;
        this.patient_address = patient_address;
        this.patient_phone = patient_phone;
        this.patient_emergencyPhone = patient_emergencyPhone;
        this.patient_age = patient_age;
    }
}
