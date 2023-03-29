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
        }
    }
}