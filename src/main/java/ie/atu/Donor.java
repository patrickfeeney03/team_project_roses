package ie.atu;

public class Donor extends User {
    private String bloodType;
    private String Last_Med;
    private String First_Med;
    public Donor(int user_Id, String user_email, String user_password, String user_Name, String user_role,
                 String user_Address, String user_Phone, int user_Age, String First_Med, String Last_Med,
                 String bloodType) {
        super(user_Id, user_email, user_password, user_Name, user_role, user_Address, user_Phone, user_Age);
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
