package ie.atu;

import java.sql.SQLOutput;


public class Donor extends User {
    private String bloodType;
    private String Last_Med;
    private String First_Med;
    public Donor(String First_Med, String Last_Med, String User_Name, String user_Address, String  user_Phone, String user_Age, String user_Id ,String bloodType) {
        super(User_Name, user_Address, user_Phone,user_Age,user_Id);
        this.First_Med = First_Med;
        this.Last_Med = Last_Med;
        this.bloodType = bloodType;
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
