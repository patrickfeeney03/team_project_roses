package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserManager {
    BloodManager bloodManager = new BloodManager();
    private static PatientManager patientManager = new PatientManager();
    public Connection getConnection() throws SQLException {
        return DBConnectionUtils.getConnection();
    }

    public boolean login(Scanner scanner) {
        System.out.print("Enter email: ");
        String inputEmail = scanner.next();
        System.out.print("Enter password: ");
        String inputPassword = scanner.next();

        User user = getUserByEmail(inputEmail);

        if (user != null && Objects.equals(inputPassword, user.getUser_password())) {
            System.out.println("Login successful.");
            return true;
        }
        else {
            System.out.println("Wrong username or/and password.");
            return false;
        }
    }

    public void register(Scanner scanner) {
        System.out.print("Enter user email: ");
        String userEmail = scanner.next();
        System.out.print("Enter user password: ");
        String userPassword = scanner.next();
        System.out.print("Enter user name: ");
        String userName = scanner.next();
        System.out.print("Enter user role: ");
        String userRole = scanner.next();
        System.out.print("Enter user address: ");
        String userAddress = scanner.next();
        System.out.print("Enter user phone: ");
        String userPhone = scanner.next();
        System.out.print("Enter user age: ");
        int userAge = scanner.nextInt();

        // By setting the ID to 0, the auto-increment from SQL will automatically set the ID.
        User newUser = new User(0, userEmail, userPassword, userName, userRole, userAddress, userPhone, userAge);
        boolean wasRegistrationSuccessful = addUser(newUser);

        if (wasRegistrationSuccessful) {
            System.out.println("User registration successful.");
        }
        else {
            System.out.println("User registration failed.");
        }
    }

    public void userMenu(Scanner scanner) {
        boolean exitUserMenu = false;
        boolean patientMenu = false;
        while (!exitUserMenu) {
            System.out.print("\nUser Menu:\n1: Request Blood\n2: Record Donation\n3: View Stock\n4: View Patients\n5: Logout" +
                    "\nEnter your choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> {
                    // Request Blood
                    System.out.print("Requested Blood Group: ");
                    String inputBloodGroup = scanner.next();
                    System.out.print("Requested Rh Factor: ");
                    char inputRhFactor = scanner.next().charAt(0);
                    System.out.print("Amount of units: ");
                    int inputAmount = scanner.nextInt();
                    BloodType bloodType = new BloodType(inputBloodGroup, inputRhFactor);

                    System.out.println("Request Successful: " + bloodManager.requestBlood(bloodType, inputAmount));
                }
                case 2 -> {
                    // Record Donation
                    System.out.print("Donated Blood Group: ");
                    String inputBloodGroup = scanner.next();
                    System.out.print("Donated Rh Factor: ");
                    char inputRhFactor = scanner.next().charAt(0);
                    System.out.print("Donated units: ");
                    //
                    int inputAmount = scanner.nextInt();
                    BloodType bloodType = new BloodType(inputBloodGroup, inputRhFactor);
                    System.out.println("Donation Successful: " + bloodManager.recordDonation(bloodType, inputAmount));
                }
                case 3 -> {
                    // View Stock
                    System.out.println("Blood Stock Information:");
                    List<BloodStock> bloodStockList = bloodManager.getStock();
                    for (BloodStock bloodStock : bloodStockList) {
                        System.out.println("Blood Type: " + bloodStock.getBloodGroup() +
                                ", Amount: " + bloodStock.getAmount());
                    }
                }

                case 4 ->{
                    // View/register patients
                    while (patientMenu != true) {
                        System.out.println("Patient Information:\n1: Donor information\n2: Recipient information\n3:Logout" + "\nEnter Your Choice:");
                        int second_User_Choice = scanner.nextInt();
                        switch (second_User_Choice) {
                            //Donor information
                            case 1 -> {

                            }
                            //Recipient Information
                            case 2 -> {

                            }
                            case 3 ->
                                //Register New Donor

                            break;


                            case 4 ->
                                //Register new Recipient
                                   
                            break;


                            case 5 -> patientMenu = true;
                            default -> System.out.println("Input not valid.\n");
                        }
                    }
                }



            }
            case 5-> exitUserMenu = true;
            default -> System.out.println("Input not valid.\n");
        }
    }
}

    public boolean addUser(User user) {
        String insertSQL = "INSERT INTO user (userID, email, password, name, role, address, phone, age) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, user.getUser_Id());
            preparedStatement.setString(2, user.getUser_email());
            preparedStatement.setString(3, user.getUser_password());
            preparedStatement.setString(4, user.getUser_name());
            preparedStatement.setString(5, user.getUser_role());
            preparedStatement.setString(6, user.getUser_address());
            preparedStatement.setString(7, user.getUser_phone());
            preparedStatement.setInt(8, user.getUser_age());

            // .executeUpdate() returns the number of rows affected.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        String updateSQL = "UPDATE user SET email = ?, password = ?, name = ?, role = ?, address = ?, phone = ?," +
                "age = ? WHERE userID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, user.getUser_email());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.setString(3, user.getUser_name());
            preparedStatement.setString(4, user.getUser_role());
            preparedStatement.setString(5, user.getUser_address());
            preparedStatement.setString(6, user.getUser_phone());
            preparedStatement.setInt(7, user.getUser_age());
            preparedStatement.setInt(8, user.getUser_Id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeUser(User user) {
        String deleteSQL = "DELETE FROM user WHERE userID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, user.getUser_Id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String selectSQL = "SELECT * FROM user WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUser_Id(resultSet.getInt("userID"));
                user.setUser_email(resultSet.getString("email"));
                user.setUser_password(resultSet.getString("password"));
                user.setUser_name(resultSet.getString("name"));
                user.setUser_role(resultSet.getString("role"));
                user.setUser_address(resultSet.getString("address"));
                user.setUser_phone(resultSet.getString("phone"));
                user.setUser_age(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}