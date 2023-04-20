package ie.atu;

// This class is used by the hospital staff. This doesn't represent the actual patients.
public class User {

    private int user_Id;
    private String user_email;
    private String user_password;
    private String user_name;
    private String user_role;
    private String user_address;
    private String user_phone;
    private int user_age;

//(int id, String email, String password, String role, String address, String phone, int age)

    public User(int user_Id, String user_email, String user_password, String user_name, String user_role,
                String user_address, String user_phone, int user_age) {
        this.user_Id = user_Id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_role = user_role;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.user_age = user_age;
    }

    public User() {
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }
}



