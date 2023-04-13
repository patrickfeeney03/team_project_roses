package ie.atu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;

public class BloodDonationSystem {
    // This list is like an array but it's more dynamic. It's like an interface, in some way.
    private static List<BloodStock> bloodStockList = new ArrayList<>();
    public static void main(String[] args) {
        // Manually populating the bloodStockList to check the functionality.
        bloodStockList.add(new BloodStock(new BloodType("A", '+'), 5));
        bloodStockList.add(new BloodStock(new BloodType("O", '-'), 20));

        Scanner myScanner = new Scanner(System.in);

        BloodType bloodType = new BloodType("B", '-');

        String userName = "admin";
        String userPassword = "admin";

        // Main loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register (not working yet)");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int userChoice;
            System.out.println("Enter your choice: ");
            userChoice = myScanner.nextInt();

            switch (userChoice) {

                case 1:
                    System.out.println("'executing registering process' (not working yet)");
                    break;
                case 2:
                    String inputName;
                    String inputPassword;
                    System.out.println("Enter username: ");
                    inputName = myScanner.next();
                    System.out.println("Enter password: ");
                    inputPassword = myScanner.next();

                    if (Objects.equals(inputName, userName) && Objects.equals(inputPassword, userPassword)) {
                        System.out.println("1: Request Blood\n2: Record Donation\n3: View Stock");
                        userChoice = myScanner.nextInt();

                        switch (userChoice) {
                            case 1 -> requestBlood();
                            case 2 -> recordDonation();
                            case 3 -> viewStock();
                            default -> System.out.println("Input not valid.\n");
                        }
                    }
                    else {
                        System.out.println("Wrong password.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;

            }
        }
    }

    public static void register() {
        System.out.println("register static method");
    }

    public static void login() {
        System.out.println("login static method");
    }
    /////////////////////////////////////////////////////////

    public static void requestBlood() {
        System.out.println("requestBlood static method");
    }

    public static void recordDonation() {
        System.out.println("recordDonation static method");
    }

    public static void viewStock() {
        System.out.println("viewStock static method");
        System.out.println("Current Blood Stock:");
        System.out.println("BloodType\tAmount");


    }
}