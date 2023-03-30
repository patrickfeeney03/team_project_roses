package ie.atu;

import java.util.Scanner;

public class BloodDonationSystem {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        BloodType donorBloodType = new BloodType("B", '-');

        System.out.println("Welcome to the Blood Donation System!");

        // Main loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");


            System.out.println("Enter your choice: ");
            int userChoice = myScanner.nextInt();
            System.out.println("Choice: " + userChoice);

            System.out.println("1: Add new donor\n2: Request Blood\n3: Record Donation\n4: View Stock");

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
}