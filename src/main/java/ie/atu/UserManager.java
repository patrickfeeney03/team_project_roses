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
        scanner.nextLine();
        System.out.print("Enter user email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Enter user password: ");
        String userPassword = scanner.nextLine();
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter user role: ");
        String userRole = scanner.nextLine();
        System.out.print("Enter user address: ");
        String userAddress = scanner.nextLine();
        System.out.print("Enter user phone: ");
        String userPhone = scanner.nextLine();
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
        BloodBank bloodBank = new BloodBank(4, "bloodBank0 Email", "Galway",
                "1234131");

        boolean exitUserMenu = false;
        boolean patientMenu = false;
        while (!exitUserMenu) {
            System.out.print("\nUser Menu:\n1: Request Blood\n2: Record Donation\n3: View Stock\n4: Patients\n5: Logout" +
                    "\nEnter your choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> {
                    // REQUEST BLOOD
                    // Patient comes to the hospital/blood bank to receive blood.
                    // Nurse checks his blood type and how much amount he needs, also getting what are his compatible blood types
                    // Nurse check if he's a new patient or if he's already registered.
                    // If the patient is not in the database, register them as a new patient.
                    //

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
                    //List<String> compatibleBloodTypes = BloodManager.getCompatibleBloodTypes(bloodType);
                    //System.out.println("Compatible blood types with this recipient: " + compatibleBloodTypes);


                    // Get patient by ID. If he doesn't exist, create new patient and add it to DB
                    System.out.print("Enter the Patient ID: ");
                    int patientID = scanner.nextInt();
                    Patient singlePatientInfo = patientManager.getSinglePatientInfo(patientID);


                    Patient patient = patientManager.getSinglePatientInfo(patientID);

                    // need to print medical data, connect tables

                    if (patient != null) {
                        System.out.println("\nPatient Information:");
                        System.out.println("Patient ID: " + patient.getPatient_Id());
                        System.out.println("First Name: " + patient.getPatient_firstName());
                        System.out.println("Last Name: " + patient.getPatient_lastName());
                        System.out.println("Age: " + patient.getPatient_age());
                        System.out.println("DOB: " + patient.getPatient_DOB());
                        System.out.println("Email: " + patient.getPatient_email());
                        System.out.println("Address: " + patient.getPatient_address());
                        System.out.println("Phone: " + patient.getPatient_phone());
                        System.out.println("Emergency Phone: " + patient.getPatient_emergencyPhone());
                        System.out.println("Disease: " + patient.getPatientDisease());
                    }
                    else {
                        System.out.println("Patient ID doesn't exist\nAdd Patient to the database");
                        patientManager.register(myScanner);
                    }




                    // Recipient object is just for testing.
                    Recipient recipient = new Recipient(0, "Mikaela", "Diaz",
                            20, "08/08/2003", "mikaelEmail", "addressMikaela",
                            "123345123", "9785684834", bloodType);

                    // Create the receive object.
                    Receive receive = new Receive(recipient, bloodBank, inputAmount);


                    // Check if BloodBank details are correct. The object is already created at the top of this method.
                    System.out.println("Are these location presets correct? [Y/N] " + bloodBank.toString());
                    char bloodBankDetails = scanner.next().toUpperCase().charAt(0);

                    if(bloodBankDetails == 'N') {
                        // All blood bank details
                        List<BloodBank> bloodBanks = BloodBankManager.getAllBloodBankByID();

                        // Select the right blood bank by ID
                        System.out.print("Select the right Blood Bank by entering the ID: ");
                        int bankID = scanner.nextInt();

                        // Selected blood bank details
                        BloodBank bloodBankInfo = BloodBankManager.getBloodBankByID(bankID);
                    }

                    else {
                        // Retrieve blood from the stock (if possible with the highest amount of blood)
                        boolean updateBlood_stock = BloodStockManager.updateTable_blood_stock();



                        // We have to add an expiry date to the blood too. So the blood
                        boolean requestSuccessful =
                                bloodManager.requestBlood(receive.getRecipient().getBloodType(), receive.getUnitsReceived());

                        if (requestSuccessful) {
                            System.out.println("Request Successful: " + requestSuccessful);
                        }

                        //System.out.println("Request Successful: " + bloodManager.requestBlood(bloodType, inputAmount));
                    }
                }
                case 2 -> {
                    // DONATION
                    // Patient comes to the hospital/blood bank to donate blood.
                    // Nurse check if he's a new patient or if he's already registered.
                    // If the patient doesn't know their ID, well, they are not registered, for now.
                        // To register them, the nurse would need to go back? And choose option 3: Patients, and then
                        // Option 2.

                    // Nurse asks the patient for their ID. Patient responds
                    System.out.print("Patient's id: ");
                    int patientID = scanner.nextInt();

                    // This checks if the patientID is valid.
                    if (!patientManager.patientExistsInDB(patientID)) {
                        System.out.println("The patient with id of " + patientID + " doesn't exist in the database." +
                                "\nPlease go back and register.");
                        // This break makes the code jump back to the top of the while loop.
                        break;
                    }

                    // This two lines will try to get the bloodType of the already registered patient.
                    int donorID = PatientManager.getDonorIDFromPatientID(patientID);
                    BloodType bloodType = null;

                    if (BloodManager.get_BloodType_ID_From_PMD(patientID) != 0) {
                       bloodType = BloodManager.getBloodTypeByID(BloodManager.get_BloodType_ID_From_PMD(patientID));
                                // if patient already donor table, don't do this.
                        if (PatientManager.getDonorIDFromPatientID(patientID) == 0) {
                            // Donor doesn't exist in donor table, so we add it
                            patientManager.addPatientToDonorTable(patientID);
                            System.out.println("Added patient to donor table: " + patientID);
                        }
                    } else {
                        // If the code gets in here, it means that the patient exists but hasn't donated yet.
                        System.out.println("Patient hasn't donated blood yet. Blood details needed. ");
                        System.out.print("Blood Group: ");
                        String bloodGroup = scanner.next();
                        System.out.print("Rh factor: ");
                        char rhFactor = scanner.next().charAt(0);
                        bloodType = new BloodType(bloodGroup, rhFactor);

                        // Method that adds the patient to the donor table.
                        System.out.println("Added patient to donor table: " +
                                patientManager.addPatientToDonorTable(patientID));
                    }
                    //System.out.println("Patients blood type: " + bloodType.toString());

                    // How many units were donated
                    System.out.print("How many units of type " + bloodType.toString() + " will be donated: ");
                    int unitsDonated = scanner.nextInt();

                    // Create BloodUnit object to set the date of donation
                    // This saves the date
                    BloodUnit bloodUnit = new BloodUnit(bloodType);

                    Patient patient = patientManager.getSinglePatientInfo(patientID);
                    Donor donor = new Donor(patient, bloodType);

                    // Check if the presetted bloodBank values are correct.
                    System.out.println("Are these location presets correct? [Y/N] " + bloodBank.toString());
                    System.out.println("Assuming they are, for now, we proceed...");

                    //bloodBank.setBankID(2);
                    Donation donation = new Donation(donor, bloodBank, bloodUnit, unitsDonated);
                    System.out.println("BloodBank id: " +bloodBank.getBankID());
                    boolean donated_bloodTableSuccessful = BloodManager.addBloodToDonated_blood(donation);
                    boolean stockUpdated = BloodStockManager.updateTable_blood_stock();
                    System.out.println(stockUpdated);
                    // Updating pmd here
                    //System.out.println("getFirstDonation: " + PatientManager.getFirstDonation(patientID));
                    if (Objects.equals(PatientManager.getFirstDonation(donation.getDonor().getPatient_Id()), null)) {
                        PatientManager.setFirstDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate());
                        PatientManager.setLastDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate());
                    } else {
                        PatientManager.setLastDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate());
                    }

                    System.out.println("donated_bloodTable successful: " + donated_bloodTableSuccessful);
                    System.out.println("stock table updated: " + stockUpdated);
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

                    // View/register patients
                    while (patientMenu != true) {
                        System.out.println("\nPatient Information:\n1: View Patient Information\n2: Register New Patient" +
                                "\n3: Remove a Patient\n4:Return to first Menu\nEnter Your Choice: ");
                        int second_User_Choice = scanner.nextInt();
                        switch (second_User_Choice) {
                            //Donor information
                            case 1 -> {
                                System.out.println("\nEnter Patient ID: ");
                                int userInput = myScanner.nextInt();
                                
                                Patient patient = patientManager.getPatientInfoAll(userInput);
                                if (patient != null) {
                                    System.out.println("\nPatient Information:");
                                    System.out.println("Patient ID: " + patient.getPatient_Id());
                                    System.out.println("First Name: " + patient.getPatient_firstName());
                                    System.out.println("Last Name: " + patient.getPatient_lastName());
                                    System.out.println("Age: " + patient.getPatient_age());
                                    System.out.println("DOB: " + patient.getPatient_DOB());
                                    System.out.println("Email: " + patient.getPatient_email());
                                    System.out.println("Address: " + patient.getPatient_address());
                                    System.out.println("Phone: " + patient.getPatient_phone());
                                    System.out.println("Emergency Phone: " + patient.getPatient_emergencyPhone());
                                    System.out.println("Disease: " + patient.getPatientDisease());
                                } else {
                                    System.out.println("Patient not found.");
                                }
                            }

                            case 2 -> {
                                //Register New Donor
                                //this patient object does not need an id as sql will automatically enter one
                                //System.out.println("Enter New patient: \n");
                                patientManager.register(myScanner);
                            }

                            case 3 -> {
                                //Remove patient
                                // this patient object needs an id to be able to select which patient will be removed
                                System.out.println("Enter a patient ID to be removed: \n");
                                int userInput = myScanner.nextInt();
                                patientManager.removePatient(patientManager.getSinglePatientInfo(userInput));
                            }

                            case 4 -> {
                                //Return to first menu
                                patientMenu = true;
                            }

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