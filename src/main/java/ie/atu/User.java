package ie.atu;

public class User {

   private String user_Name;
   private String user_Address;
   private String user_Phone;
   private String user_Age;
   private String user_Id;


    public User(String user_Name, String user_Address, String user_Phone, String user_Age, String user_Id) {
        this.user_Name = user_Name;
        this.user_Address = user_Address;
        this.user_Phone = user_Phone;
        this.user_Age = user_Age;
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Address() {
        return user_Address;
    }

    public void setUser_Address(String user_Address) {
        this.user_Address = user_Address;
    }

    public String getUser_Age() {
        return user_Age;
    }

    public void setUser_Age(String user_Age) {
        this.user_Age = user_Age;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }
}
