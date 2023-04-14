package ie.atu;

import java.sql.*;

public class User {

    private int user_Id;
    private String user_email;
    private String user_password;
    private String user_Name;
    private String user_role;
    private String user_Address;
    private String user_Phone;
    private int user_Age;

//(int id, String email, String password, String role, String address, String phone, int age)


    public User(int user_Id, String user_email, String user_password, String user_Name, String user_role,
                String user_Address, String user_Phone, int user_Age) {
        this.user_Id = user_Id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_Name = user_Name;
        this.user_role = user_role;
        this.user_Address = user_Address;
        this.user_Phone = user_Phone;
        this.user_Age = user_Age;
    }

    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public static void main(String[] args) {
        //User user1 = new User()
        testingDatabaseOperation();
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

    public int getUser_Age() {
        return user_Age;
    }

    public void setUser_Age(int user_Age) {
        this.user_Age = user_Age;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public static void testingDatabaseOperation() {
        String selectSQL =
                "SELECT * FROM user";
        try (Connection connection = DBConnectionUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                System.out.println(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(int id, String email, String password, String role, String address, String phone, int age) {
        String insertSQL = "INSERT INTO user (userID, email, password, role, address, phone, age) " +
                "VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%d')";
        String formattedSQL = String.format(insertSQL, id, email, password, role, address, phone, age);

        try (Connection connection = DBConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(formattedSQL)) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



