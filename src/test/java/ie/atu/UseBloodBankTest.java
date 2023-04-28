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
        BloodBank bloodBank = new BloodBank(0, "email", "address",
                "thisIsAPhoneNumber");
        Scanner scanner = new Scanner(System.in);

        System.out.println(bloodBank.toString());
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
                int bankID = scanner.nextInt();
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
        Donor donor = new Donor(0, "Patrick", "Feeney", 19,
                "15/12/2003", "emailpatrick", "patricksAddress", "999222212",
                "393298292", bloodType);
        Donation donation = new Donation(donor, bloodBank, 1);
        System.out.println(donor.toString());
        System.out.println(donation.getBloodBank().getClass());
    }
}
