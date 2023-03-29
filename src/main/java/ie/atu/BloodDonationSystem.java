package ie.atu;

import java.util.Scanner;

public class BloodDonationSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to the Blood Donation System!");

        // Main loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            Scanner myScanner = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            int userChoice = myScanner.nextInt();
            System.out.println("Choice: " + userChoice);

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
                case 5:
                    // Exit?
                    break;
                default:
                    System.out.println("Input not valid.\n");
            }
        }
    }
}