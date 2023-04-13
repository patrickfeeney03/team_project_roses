package ie.atu;

import java.util.Scanner;

public class BloodDonationSystem {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        BloodType bloodType = new BloodType("B", '-');

        // Main loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int userChoice;
            System.out.println("Enter your choice: ");
            userChoice = myScanner.nextInt();

            System.out.println("1: Request Blood\n2: Record Donation\n3: View Stock");
            userChoice = myScanner.nextInt();

            switch (userChoice) {
                case 1:
                    // New donor?
                    break;
                case 2:
                    // Request donation/blood?
                    break;
                case 3:
                    // Add donation. Record it to db.
                    break;
                case 4:
                    // We need to also be able to view how much blood we have.
                    break;
                default:
                    System.out.println("Input not valid.\n");
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
    }
}