package ie.atu;

public class Recipient extends User {

    private String recipient_Disease;
    private String transfusion_History;

    private String recipient_Bloodtype;

    public Recipient(String user_Name, String user_Address, String  user_Phone, String user_Age, String user_Id,String recipient_Disease,String transfusion_History,String recipient_Bloodtype) {
        super(user_Name, user_Address, user_Phone,user_Age,user_Id);
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

