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
    PatientManager patientManager = new PatientManager();
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
        Scanner myScanner = new Scanner(System.in);
        BloodBank bloodBank = new BloodBank(0, "bloodBank0 Email", "Galway",
                "1234131");

        boolean exitUserMenu = false;
        boolean patientMenu = false;
        while (!exitUserMenu) {
            System.out.print("\nUser Menu:\n1: Request Blood\n2: Record Donation\n3: View Stock\n4: Patients\n5: Logout" +
                    "\nEnter your choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> {
                    // Ask for recipient's Blood Details
                    System.out.print("Recipient's Blood Group: ");
                    String inputBloodGroup = scanner.next();
                    System.out.print("Recipient's Rh Factor: ");
                    char inputRhFactor = scanner.next().charAt(0);

                    // How many units does the recipient need
                    System.out.print("Amount of units required: ");
                    int inputAmount = scanner.nextInt();

                    // Create BloodType object using the recipient's Blood Details
                    BloodType bloodType = new BloodType(inputBloodGroup, inputRhFactor);

                    // What blood types are compatible with the recipient's blood type?
                    List<String> compatibleBloodTypes = BloodManager.getCompatibleBloodTypes(bloodType);
                    System.out.println("Compatible blood types with this recipient: " + compatibleBloodTypes);

                    // Get recipient details. From DB or from terminal input.


                        // Get patient by id. If he doesn't exist, create new patient and add it to DB
                        // This recipient object is just for testing.
                    Recipient recipient = new Recipient(0, "Mikaela", "Diaz",
                            20, "08/08/2003", "mikaelEmail", "addressMikaela",
                            "123345123", "9785684834", bloodType);

                    // Create the receive object.
                    Receive receive = new Receive(recipient, bloodBank, inputAmount);

                    // Check if BloodBank details are correct. The object is already created at the top of this method.
                    System.out.println("Are these location presets correct? [Y/N] " + bloodBank.toString());
                    char bloodBankDetails = scanner.next().toUpperCase().charAt(0);
                    // Assuming they are correct...

                    // Retrieve blood from the stock with the highest amount of blood
                     // We have to add an expiry date to the blood too. So the blood
                    boolean requestSuccessful =
                            bloodManager.requestBlood(receive.getRecipient().getBloodType(), receive.getUnitsReceived());

                    if (requestSuccessful) {
                        System.out.println("Request Successful: " + requestSuccessful);
                    }

                    //System.out.println("Request Successful: " + bloodManager.requestBlood(bloodType, inputAmount));
                }
                case 2 -> {
                    // DONATION

                    // Patient comes to the hospital/blood bank to donate blood.
                    // Nurse check if he's a new patient or if he's already registered.
                    // If the patient doesn't know their ID, well, they are not registered, for now.



                    // Nurse asks the patient for their ID. Patient responds
                    System.out.println("Patients id: ");
                    int examplePatientID = scanner.nextInt();

                    //

                    // Get the blood type of the already registered patient.
                    int donorID = PatientManager.getDonorIDFromPatientID(examplePatientID);
                    String donorBloodTypeString = BloodManager.getDonorBloodTypeString(donorID);
                    // Ask for donor's Blood Details
                    System.out.print("Donated Blood Group: ");
                    String inputBloodGroup = scanner.next();
                    System.out.print("Donated Rh Factor: ");
                    char inputRhFactor = scanner.next().charAt(0);

                    // How many units were donated
                    System.out.print("Units donated: ");
                    int unitsDonated = scanner.nextInt();

                    // Create BloodType of the donated blood.
                    BloodType bloodType = new BloodType(inputBloodGroup, inputRhFactor);

                    //Create BloodUnit object to set the date of donation
                    BloodUnit bloodUnit = new BloodUnit(bloodType);



                    System.out.println("The patient with id " + examplePatientID + " has " + donorBloodTypeString +
                            " blood type.");
                    //Donation donation = new Donation(0, donor, bloodBank, bloodUnit, unitsDonated);

                    // Check if the presetted bloodBank values are correct.
                    System.out.println("Are these location presets correct? [Y/N] " + bloodBank.toString());
                    // Assuming they are correct...

                    //System.out.println("Donation Successful: " + bloodManager.recordDonation
                            //(donation.getDonor().getBloodType(), donation.getUnitsDonated()));
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

                case 4 -> {
                    Patient patient = null;
                    // View/register patients
                    while (patientMenu != true) {
                        System.out.println("\nPatient Information:\n1: View Patient Information\n2: Register New Patient\n3: Remove a Patient\n4:Logout\nEnter Your Choice: ");
                        int second_User_Choice = scanner.nextInt();
                        switch (second_User_Choice) {
                            //Donor information
                            case 1 -> {
                                System.out.println("\nEnter Patient ID: ");
                                int userInput = myScanner.nextInt();
                                //patientManager.getSinglePatientInfo(userInput);

                                // Check SQL tables to see if patient is donor, recipient, or both
                                String checkPatient = "SELECT donor AS table_name, patient_info.* " +
                                "FROM donor " +
                                "JOIN patient_info ON donor.corresponding_patient_id = patient_info.patientID " +
                                "UNION " +
                                "SELECT recipient AS table_name, patient_info.* " +
                                "FROM recipient " +
                                "JOIN patient_info ON recipient.corresponding_patient_id = patient_info.patientID ";

                                try (Connection connection = DBConnectionUtils.getConnection();
                                    PreparedStatement preparedStatement = connection.prepareStatement(checkPatient)) {
                                    preparedStatement.setInt(1, userInput);

                                    ResultSet resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        patient = new Patient();
                                        patient.setPatient_Id(resultSet.getInt("patientID"));
                                        patient.setPatient_firstName(resultSet.getString("patientFirstName"));
                                        patient.setPatient_lastName(resultSet.getString("patientLastName"));
                                        patient.setPatient_age(resultSet.getInt("patientAge"));
                                        patient.setPatient_DOB(resultSet.getString("patientDOB"));
                                        patient.setPatient_email(resultSet.getString("patientEmail"));
                                        patient.setPatient_address(resultSet.getString("patientAddress"));
                                        patient.setPatient_phone(resultSet.getString("patientPhone"));
                                        patient.setPatient_emergencyPhone(resultSet.getString("patientEmergencyPhone"));

                                        System.out.println("patientID: " + patient.getPatient_Id());
                                        System.out.println("patientFirstName: " + patient.getPatient_firstName());
                                        System.out.println("patientLastName: " + patient.getPatient_lastName());
                                        System.out.println("patientAge: " + patient.getPatient_age());
                                        System.out.println("patientDOB: " + patient.getPatient_DOB());
                                        System.out.println("patientEmail: " + patient.getPatient_email());
                                        System.out.println("patientAddress: " + patient.getPatient_address());
                                        System.out.println("patientPhone: " + patient.getPatient_phone());
                                        System.out.println("patientEmergencyPhone: " + patient.getPatient_emergencyPhone());
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }

                            case 2 -> {
                                //Register new patient
                                System.out.println("\nEnter New patient: ");
                                patient = new Patient(0, "alan",
                                        "hynes", 23, "20.04.2000",
                                        "alanEmail", "South Park",
                                        "086809765", "08976542");
                                patientManager.addPatient(patient);
                            }

                            case 3 -> {
                                //Register New Donor
                                //this patient object does not need an id as sql will automatically enter one

                                System.out.println("Enter New patient: \n");
                                patientManager.register(myScanner);
                            }

                            case 4 -> {

                                //Remove patient
                                // this patient object needs an id to be able to select which patient will be removed
                                System.out.println("Enter a patient ID to be removed: \n");
                                int userInput = myScanner.nextInt();
                                patientManager.removePatient(patientManager.getSinglePatientInfo(userInput));
                            }

                            //case 4 -> patientMenu = true;

                            default -> System.out.println("Input not valid.\n");
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