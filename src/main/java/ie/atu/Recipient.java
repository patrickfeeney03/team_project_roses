package ie.atu;

public class Recipient extends User {

    private String Recipient_Disease;
    private String Transfusion_History;

    private String Recipient_Bloodtype;

    public Recipient(String user_Name, String user_Address, String  user_Phone, String user_Age, String user_Id,String recipient_Disease,String transfusion_History,String recipient_Bloodtype) {
        super(user_Name, user_Address, user_Phone,user_Age,user_Id);
        this.Recipient_Disease = recipient_Disease;
        this.Transfusion_History = transfusion_History;
        this.Recipient_Bloodtype = recipient_Bloodtype;
    }

    public String getRecipient_Disease() {
        return Recipient_Disease;
    }

    public void setRecipient_Disease(String recipient_Disease) {
        Recipient_Disease = recipient_Disease;
    }

    public String getTransfusion_History() {
        return Transfusion_History;
    }

    public void setTransfusion_History(String transfusion_History) {
        Transfusion_History = transfusion_History;
    }

    public String getRecipient_Bloodtype() {
        return Recipient_Bloodtype;
    }

    public void setRecipient_Bloodtype(String recipient_Bloodtype) {
        Recipient_Bloodtype = recipient_Bloodtype;
    }
}

