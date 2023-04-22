package ie.atu;

public class Recipient extends Patient {

    private String recipient_Disease;
    private String transfusion_History;

    private String recipient_Bloodtype;


    public Recipient(int patient_Id, String patient_firstName, String patient_lastName, int patient_age, String patient_DOB, String patient_email, String patient_address, String patient_phone, String patient_emergencyPhone, String recipient_Bloodtype,String transfusion_History, String recipient_Disease) {
        super(patient_Id, patient_firstName, patient_lastName, patient_age, patient_DOB, patient_email, patient_address, patient_phone, patient_emergencyPhone);
        this.recipient_Disease = recipient_Disease;
        this.transfusion_History = transfusion_History;
        this.recipient_Bloodtype = recipient_Bloodtype;
    }

    public String getRecipient_Disease() {
        return recipient_Disease;
    }

    public void setRecipient_Disease(String recipient_Disease) {
        this.recipient_Disease = recipient_Disease;
    }

    public String getTransfusion_History() {
        return transfusion_History;
    }

    public void setTransfusion_History(String transfusion_History) {
        this.transfusion_History = transfusion_History;
    }

    public String getRecipient_Bloodtype() {
        return recipient_Bloodtype;
    }

    public void setRecipient_Bloodtype(String recipient_Bloodtype) {
        this.recipient_Bloodtype = recipient_Bloodtype;
    }
}

