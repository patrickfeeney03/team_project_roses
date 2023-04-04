package ie.atu;

public class User {

   private String User_Name;
   private String User_Address;
   private String User_Phone;
   private String User_Age;
   private String User_Id;


    public User(String user_Name, String user_Address, String user_Phone, String user_Age, String user_Id) {
        this.User_Name = user_Name;
        this.User_Address = user_Address;
        this.User_Phone = user_Phone;
        this.User_Age = user_Age;
        this.User_Id = user_Id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Address() {
        return User_Address;
    }

    public void setUser_Address(String user_Address) {
        User_Address = user_Address;
    }

    public String getUser_Age() {
        return User_Age;
    }

    public void setUser_Age(String user_Age) {
        User_Age = user_Age;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getUser_Phone() {
        return User_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        User_Phone = user_Phone;
    }
}
