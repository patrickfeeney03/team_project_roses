package ie.atu;

import java.util.Scanner;

public class UseBloodBankTest {
    public static void main(String[] args) {
        /*
        So, before performing a donation, the bloodBank details will be already defined, since
        a blood bank is a place, which doesn't really change, except if the nurse (user)
        moves to a different location.
        */
        // bloodBank gets created at the beginning of the code execution.
        BloodBank bloodBank = new BloodBank("2", "email", "address",
                "thisIsAPhoneNumber");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Session presets: "  + "\nBlood Bank ID: " + bloodBank.getBankID() +
                "\nBlood Bank Email: " + bloodBank.getBankEmail() + "\nBlood Bank Address: " + bloodBank.getBankAddress() +
                "\nBlood Bank Phone Number: " + bloodBank.getBankPhone());
        boolean exit = false;
        do {
            System.out.print("Are these details correct? (Y/N): ");
            char userInput = scanner.next().toUpperCase().charAt(0);
            if (userInput == 'Y') {
                System.out.println("Details are correct. Proceeding");
                exit = true;
            }
            else if (userInput == 'N') {
                System.out.print("Enter the bank ID: ");
                String bankID = scanner.next();
                System.out.print("Enter email: ");
                String bankEmail = scanner.next();
                System.out.print("Enter address: ");
                String bankAddress = scanner.next();
                System.out.print("Enter phone number: ");
                String bankPhone = scanner.next();
                bloodBank.setBankID(bankID);
                bloodBank.setBankEmail(bankEmail);
                bloodBank.setBankAddress(bankAddress);
                bloodBank.setBankPhone(bankPhone);
                exit = true;
            }
            else {
                System.out.println("Input not valid. Try again...");
            }

        } while (!exit);

        BloodType bloodType = new BloodType("A", '+');
        Donor donor = new Donor(2, "email", "password", "name",
                "noRole", "123Address", "999", 20, "Yesterday",
                "Today", bloodType.toString());
        Donation donation = new Donation(donor, bloodBank, 1);
        System.out.println(donation);
    }



}
