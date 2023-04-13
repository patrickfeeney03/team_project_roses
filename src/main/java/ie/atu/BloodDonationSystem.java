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
                    login(myScanner);
                case 3:
                    exit = true;
                    break;

            }
        }
    }

    public static void register() {
        System.out.println("register static method");
    }

    public static void login(Scanner lmyScanner) {
        System.out.println("login static method");
        String userName = "admin";
        String userPassword = "admin";

        System.out.println("Enter user name: ");
        String inputName = lmyScanner.next();
        System.out.println("Enter password: ");
        String inputPassword = lmyScanner.next();

        if (Objects.equals(inputName, userName) && Objects.equals(inputPassword, userPassword)) {
            userMenu(lmyScanner);
        }
        else {
            System.out.println("Wrong password.");
        }
    }

    public static void userMenu(Scanner lmyScanner) {
        boolean exitUserMenu = false;
        while (!exitUserMenu) {
            System.out.println("1: Request Blood\n2: Record Donation\n3: View Stock\n4: Logout");
            int userChoice = lmyScanner.nextInt();

            switch (userChoice) {
                case 1 -> requestBlood(lmyScanner);
                case 2 -> recordDonation(lmyScanner);
                case 3 -> viewStock();
                case 4 -> exitUserMenu = true;
                default -> System.out.println("Input not valid.\n");
            }
        }
    }

    public static void requestBlood(Scanner lmyScanner) {
        System.out.println("requestBlood static method");

        System.out.println("Enter the blood group (A, B, AB, O):");
        String bloodGroup = lmyScanner.next().toUpperCase();

        System.out.println("Enter the Rh factor (+ or -):");
        char rhFactor = lmyScanner.next().charAt(0);

        System.out.println("Enter the amount of units needed:");
        int requestedAmount = lmyScanner.nextInt();

        BloodType requestedBloodType = new BloodType(bloodGroup, rhFactor);
        int availableAmount = getAvailableBloodStock(requestedBloodType);

        if (requestedAmount <= availableAmount) {
            System.out.println("Request sucessfull. " + requestedAmount + " units of " +
                    requestedBloodType + " blood will be provided.");
            updateBloodStock(requestedBloodType, requestedAmount);
            System.out.println("New stock: " + getAvailableBloodStock(requestedBloodType));
        }
        else {
            System.out.println("Request unsuccessful. Insufficient stock for " + requestedBloodType +
                    ". Only " + availableAmount + " units available.");
        }
    }

    public static int getAvailableBloodStock(BloodType requestedBloodType) {
        for (BloodStock stock : bloodStockList) {
            if (stock.getBloodGroup().getBloodGroup().equals(requestedBloodType.getBloodGroup()) &&
            stock.getBloodGroup().getRhFactor() == requestedBloodType.getRhFactor()) {
                return stock.getAmount();
            }
        }
        return 0;
    }

    public static void updateBloodStock(BloodType requestedBloodType, int amount) {
        for (BloodStock stock : bloodStockList) {
            if (stock.getBloodGroup().getBloodGroup().equals(requestedBloodType.getBloodGroup()) &&
                    stock.getBloodGroup().getRhFactor() == requestedBloodType.getRhFactor()) {
                stock.setAmount(stock.getAmount() - amount);
            }
        }
    }

    public static void recordDonation(Scanner lmyScanner) {
        System.out.println("recordDonation static method");

        // Verify user input
        String bloodGroup;
        do {
            System.out.println("Enter the blood group (A, B, AB, O):");
            bloodGroup = lmyScanner.next().toUpperCase();
        } while (!bloodGroup.matches("A|B|AB|O"));
        char rhFactor;
        do {
            System.out.println("Enter the Rh factor (+ or -):");
            rhFactor = lmyScanner.next().charAt(0);
        } while (rhFactor != '+' && rhFactor != '-');

        System.out.println("Enter the amount of units donated:");
        int donatedAmount = lmyScanner.nextInt();

        BloodType donatedBloodType = new BloodType(bloodGroup, rhFactor);
        boolean bloodTypeAlreadyExists = false;
        for (BloodStock stock : bloodStockList) {
            if (stock.getBloodGroup().getBloodGroup().equals(donatedBloodType.getBloodGroup()) &&
            stock.getBloodGroup().getRhFactor() == donatedBloodType.getRhFactor()) {
                stock.setAmount(stock.getAmount() + donatedAmount);
                System.out.println("Donation recorded. " + donatedAmount + " units of " + donatedBloodType +
                        " blood added to the stock.");
                bloodTypeAlreadyExists = true;
                break;
            }
        }

        if (!bloodTypeAlreadyExists) {
            bloodStockList.add(new BloodStock(donatedBloodType, donatedAmount));
            System.out.println("Donation recorded. " + donatedAmount + " units of "  + donatedBloodType +
                    " blood added as a new stock.");
        }
    }

    public static void viewStock() {
        System.out.println("viewStock static method");
        System.out.println("Current Blood Stock:");
        System.out.println("BloodType\tAmount");

        // Enhanced for loop
        // for each BloodStock object stock in bloodStockList...
        for (BloodStock stock : bloodStockList) {
            System.out.println(stock.getBloodGroup() + "\t\t\t" + stock.getAmount());
        }

    }
}