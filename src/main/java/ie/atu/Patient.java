package ie.atu;

public class Patient {
    private int patient_Id;
    private String patient_email;
    private String patient_firstName;
    private String patient_lastName;
    private String patient_address;
    private String patient_phone;
    private String patient_emergencyPhone;
    private int patient_age;

    public Patient(int patient_Id, String patient_email, String patient_firstName, String patient_lastName,
                   String patient_address, String patient_phone, String patient_emergencyPhone, int patient_age) {
        this.patient_Id = patient_Id;
        this.patient_email = patient_email;
        this.patient_firstName = patient_firstName;
        this.patient_lastName = patient_lastName;
        this.patient_address = patient_address;
        this.patient_phone = patient_phone;
        this.patient_emergencyPhone = patient_emergencyPhone;
        this.patient_age = patient_age;
    }

    public int getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(int patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }


    public String getPatient_firstName() {
        return patient_firstName;
    }

    public void setPatient_firstName(String patient_firstName) {
        this.patient_firstName = patient_firstName;
    }

    public String getPatient_lastName() {
        return patient_lastName;
    }

    public void setPatient_lastName(String patient_lastName) {
        this.patient_lastName = patient_lastName;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public int getPatient_emergencyPhone() {
        return patient_emergencyPhone;
    }

    public void setPatient_emergencyPhone(String patient_emergencyPhone) {
        this.patient_emergencyPhone = patient_emergencyPhone;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }
}
