package ie.atu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public static BloodBank chooseBloodBank(Scanner scanner) {
        // Make user choose the Blood Bank to be used for the current session
        System.out.println("\nPlease choose the blood bank where you are right now. Blood banks: ");
        List<BloodBank> bloodBankList = new ArrayList<>();
        bloodBankList = BloodBankManager.getAllBloodBanks();
        for (BloodBank oneBank : bloodBankList) {
            System.out.printf("ID: %-5s Email: %-30s Address: %-35s Phone: %s%n",
                    oneBank.getBankID(), oneBank.getBankEmail(), oneBank.getBankAddress(), oneBank.getBankPhone());
        }
        System.out.print("Input: ");
        int userInputBloodBankID = scanner.nextInt();
        BloodBank bloodBank = BloodBankManager.getBloodBankByID(userInputBloodBankID);

        return bloodBank;
    }

    // Code from Case 1
    public static int getRegisteredPatientIDWithPrompt(Scanner scanner) {
        PatientManager patientManager = new PatientManager();
        System.out.println("Enter the patient id: ");
        int patientID = scanner.nextInt();
        if (!patientManager.patientExistsInDB(patientID)) {
            System.out.println("The patient with id of " + patientID + " doesn't exist in the database." +
                    "\nPlease go back and register.");
            return 0;
        }
        return patientID;
    }

    public static BloodType createBloodTypeObject(int patientID, Scanner scanner) {
        // Get the bloodType of the recipient from the recipient table
        BloodType bloodType = null;
        PatientManager patientManager = new PatientManager();

        if (BloodManager.get_BloodType_ID_From_PMD(patientID) != 0) {
            bloodType = BloodManager.getBloodTypeByID(BloodManager.get_BloodType_ID_From_PMD(patientID));
        } else {
            System.out.println("Patient hasn't donated blood yet. Blood details needed. ");
            System.out.print("Blood Group: ");
            String bloodGroup = scanner.next();
            System.out.print("Rh factor: ");
            char rhFactor = scanner.next().charAt(0);
            bloodType = new BloodType(bloodGroup, rhFactor);

            // Method that adds the patient to the donor table.
            if (PatientManager.getRecipientIDFromPatientID(patientID) == 0) {
                System.out.println("Donor table updated successfully: " +
                        patientManager.addPatientToRecipientTable(patientID));
            }
        }
        return bloodType;
    }

    public static Receive createReceiveObject(BloodBank bloodBank, int patientID, BloodType bloodType,
    Scanner scanner) {
        Receive receive = null;
        PatientManager patientManager = new PatientManager();

        // How many units does the recipient need
        System.out.print("Amount of units required: ");
        int unitsRequired = scanner.nextInt();

        // Save the date to add it to the receive
        BloodUnit bloodUnit = new BloodUnit(bloodType);

        Patient patient = patientManager.getSinglePatientInfo(patientID);
        Recipient recipient = new Recipient(patient, bloodType);
        receive = new Receive(recipient, bloodBank, unitsRequired, bloodUnit);

        return receive;
    }
    public static boolean patientIsNotRecipientYet(int patientID) {
        PatientManager patientManager = new PatientManager();
        if (PatientManager.getRecipientIDFromPatientID(patientID) == 0) {
            System.out.println("Recipient table updated successfully: " +
                    patientManager.addPatientToRecipientTable(patientID));
            return true;
        }
        return false;
    }



    public void userMenu(Scanner scanner) {
        Scanner myScanner = new Scanner(System.in);

        BloodBank bloodBank = chooseBloodBank(scanner);

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

                    int patientID = getRegisteredPatientIDWithPrompt(scanner);
                    if (patientID == 0) break;
                    BloodType bloodType = createBloodTypeObject(patientID, scanner);
                    Receive receive = createReceiveObject(bloodBank, patientID, bloodType, scanner);

                    List<String> compatibleTypes = BloodManager.getCompatibleBloodTypes(bloodType);
                    System.out.println("compatible types: " + compatibleTypes);

                    List<BloodUnit> bloodUnitsList = new ArrayList<>();
                    List<Integer> allIDs = new ArrayList<>();

                    int unitsRequired = receive.getUnitsReceived();
                    for (int i = 0; i < compatibleTypes.size(); i++) {
                        BloodType bloodType2 = new BloodType();
                        bloodType2.setBloodGroup(compatibleTypes.get(i).substring(0, compatibleTypes.get(i).length() - 1));
                        bloodType2.setRhFactor(compatibleTypes.get(i).charAt(compatibleTypes.get(i).length() - 1));

                        List<Integer> listOfUnitsIDs = new ArrayList<>();
                        listOfUnitsIDs = BloodUnitManager.getBestBloodByDateList20(bloodType2.toString());
                        for (Integer currentID : listOfUnitsIDs) {
                            allIDs.add(currentID);
                        }
                    }

                    for (Integer numericUnitID : allIDs) {
                        BloodType bloodTypeLoop = BloodUnitManager.getBloodTypeWithUnitID(numericUnitID);
                        BloodUnit bloodUnitLoop = new BloodUnit(bloodTypeLoop, numericUnitID);
                        bloodUnitsList.add(bloodUnitLoop);
                    }
                    System.out.println("Possible Units: " + allIDs);
                    System.out.println("Blood unit list: " + bloodUnitsList);
                    Collections.shuffle(bloodUnitsList);
                    System.out.println("After shuffle  : " + bloodUnitsList);
                    // Actually choosing the blood units here from the retrieved ones

                    if (bloodUnitsList.size() < unitsRequired) {
                        System.out.println("Not enough blood.");
                    } else {
                        System.out.println("Recipient table updated successfully: " +
                                UserManager.patientIsNotRecipientYet(patientID));
                        for (int i = 0; i < bloodUnitsList.size(); i++) {
                            if (i >= unitsRequired) break;
                            BloodUnit unit = bloodUnitsList.get(i);
                            BloodManager.setFlagDonatedBlood(unit.getBloodIDSQL());
                            receive.getBloodUnit().setBloodIDSQL(unit.getBloodIDSQL());
                            BloodManager.addBloodToReceived_blood(receive);
                        }
                    }
                    BloodStockManager.updateTable_blood_stock();

                    // Update date columns in pmd
                    if (bloodUnitsList.size() < unitsRequired) {
                        if (Objects.equals(PatientManager.getFirstReceive(receive.getRecipient().getPatient_Id()), null)) {
                            System.out.println("First receive column updated successfully: " +
                                    PatientManager.setFirstDonation(receive.getRecipient().getPatient_Id(),
                                            receive.getBloodUnit().getDate()));
                            System.out.println("Last donation column updated successfully: " +
                                    PatientManager.setLastDonation(receive.getRecipient().getPatient_Id(),
                                            receive.getBloodUnit().getDate()));
                        } else {
                            PatientManager.setLastDonation(receive.getRecipient().getPatient_Id(),
                                    receive.getBloodUnit().getDate());
                        }
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
                            System.out.println("Donor table updated successfully: " +
                                    patientManager.addPatientToDonorTable(patientID));
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
                        if (PatientManager.getDonorIDFromPatientID(patientID) == 0) {
                            System.out.println("Donor table updated successfully: " +
                                    patientManager.addPatientToDonorTable(patientID));
                        }
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

                    //bloodBank.setBankID(2);
                    Donation donation = new Donation(donor, bloodBank, bloodUnit, unitsDonated);
                    //System.out.println("BloodBank id: " +bloodBank.getBankID());
                    boolean donated_bloodTableSuccessful = BloodManager.addBloodToDonated_blood(donation);
                    boolean stockUpdated = BloodStockManager.updateTable_blood_stock();

                    // Updating pmd here
                    //System.out.println("getFirstDonation: " + PatientManager.getFirstDonation(patientID));
                    if (Objects.equals(PatientManager.getFirstDonation(donation.getDonor().getPatient_Id()), null)) {
                        System.out.println("First donation column updated successfully: " +
                                PatientManager.setFirstDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate()));
                        System.out.println("Last donation column updated successfully: " +
                                PatientManager.setLastDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate()));
                    } else {
                        PatientManager.setLastDonation(donation.getDonor().getPatient_Id(),
                                donation.getBloodUnit().getDate());
                    }
                    System.out.println("Donated Blood table updated successfully: " + donated_bloodTableSuccessful);
                    System.out.println("Stock table updated successfully: " + stockUpdated);
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
                                    System.out.println("First Donation: " + patient.getFirstDonation());
                                    System.out.println("Last Donation: " + patient.getLastDonation());
                                    System.out.println("First Receive: " + patient.getFirstReceive());
                                    System.out.println("Last Receive: " + patient.getLastReceive());
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
                                System.out.print("Enter a patient ID to be removed: ");
                                int userInput = myScanner.nextInt();
                                Patient patientToRemove = patientManager.getSinglePatientInfo(userInput);
                                //System.out.println(patientToRemove);
                                if (patientToRemove != null) {
                                    patientManager.removePatientRecipient(patientToRemove);
                                    patientManager.removePatientDonor(patientToRemove);
                                    patientManager.removePatientPMD(patientToRemove);
                                    patientManager.removePatient(patientToRemove);
                                } else {
                                    System.out.println("Patient not found");
                                }
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