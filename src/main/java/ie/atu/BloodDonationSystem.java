package ie.atu;

import java.util.Scanner;

public class BloodDonationSystem {
    private static UserManager userManager = new UserManager();
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        // Main loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int userChoice;
            System.out.print("Enter your choice: ");
            userChoice = myScanner.nextInt();

            switch (userChoice) {
                case 1:
                    userManager.register(myScanner);
                    break;
                case 2:
                    boolean loginAttempt = userManager.login(myScanner);
                    if (loginAttempt) {
                        userManager.userMenu(myScanner);
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}