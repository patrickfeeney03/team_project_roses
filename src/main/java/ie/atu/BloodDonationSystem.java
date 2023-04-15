package ie.atu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BloodDonationSystem {
    private static UserManager userManager = new UserManager();
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
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int userChoice;
            System.out.println("Enter your choice: ");
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
                case 3:
                    exit = true;
                    break;

            }
        }
    }
}