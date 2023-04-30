package ie.atu;

public class Patient {
    private int patient_Id;
    private String patient_firstName;
    private String patient_lastName;
    private int patient_age;
    private String patient_DOB;
    private String patient_email;
    private String patient_address;
    private String patient_phone;
    private String patient_emergencyPhone;
    private String last_visit;
    private String register_date;
    private String lastReceive;
    private String firstReceive;
    private String lastDonation;
    private String firstDonation;


    public Patient(int patient_Id, String patient_firstName, String patient_lastName, int patient_age, String patient_DOB,
                   String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone) {
        this.patient_Id = patient_Id;
        this.patient_firstName = patient_firstName;
        this.patient_lastName = patient_lastName;
        this.patient_age = patient_age;
        this.patient_DOB = patient_DOB;
        this.patient_email = patient_email;
        this.patient_address = patient_address;
        this.patient_phone = patient_phone;
        this.patient_emergencyPhone = patient_emergencyPhone;
    }

    public Patient(String patient_firstName, String patient_lastName, int patient_age, String patient_DOB,
                   String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone /*String last_visit, String register_date*/) {

        this.patient_firstName = patient_firstName;
        this.patient_lastName = patient_lastName;
        this.patient_age = patient_age;
        this.patient_DOB = patient_DOB;
        this.patient_email = patient_email;
        this.patient_address = patient_address;
        this.patient_phone = patient_phone;
        this.patient_emergencyPhone = patient_emergencyPhone;
       /* this.last_visit = last_visit;
        this.register_date = register_date;*/
    }

    public Patient() {
    }

    public int getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(int patient_Id) {
        this.patient_Id = patient_Id;
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

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_DOB() {
        return patient_DOB;
    }

    public void setPatient_DOB(String patient_DOB) {
        this.patient_DOB = patient_DOB;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
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

    public String getPatient_emergencyPhone() {
        return patient_emergencyPhone;
    }

    public void setPatient_emergencyPhone(String patient_emergencyPhone) {
        this.patient_emergencyPhone = patient_emergencyPhone;
    }
    
    public String getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(String last_visit) {
        this.last_visit = last_visit;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getLastReceive() {
        return lastReceive;
    }

    public void setLastReceive(String lastReceive) {
        this.lastReceive = lastReceive;
    }

    public String getFirstReceive() {
        return firstReceive;
    }

    public void setFirstReceive(String firstReceive) {
        this.firstReceive = firstReceive;
    }

    public String getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }

    public String getFirstDonation() {
        return firstDonation;
    }

    public void setFirstDonation(String firstDonation) {
        this.firstDonation = firstDonation;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_Id=" + patient_Id +
                ", patient_firstName='" + patient_firstName + '\'' +
                ", patient_lastName='" + patient_lastName + '\'' +
                ", patient_age=" + patient_age +
                ", patient_DOB='" + patient_DOB + '\'' +
                ", patient_email='" + patient_email + '\'' +
                ", patient_address='" + patient_address + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", patient_emergencyPhone='" + patient_emergencyPhone + '\'' +
                ", last_visit='" + last_visit + '\'' +
                ", register_date='" + register_date + '\'' +
                '}';
    }
}