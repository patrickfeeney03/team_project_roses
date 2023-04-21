package ie.atu;

public class UseBloodBankTest {
    public static void main(String[] args) {
        /*
        So, before performing a donation, the bloodBank details will be already defined, since
        a blood bank is a place, which doesn't really change, except if the nurse (user)
        moves to a different location.
        */
        BloodBank bloodBank = new BloodBank("2", "email", "address",
                "thisIsAPhoneNumber");
        BloodType bloodType = new BloodType("A", '+');
        Donor donor = new Donor(2, "email", "password", "name",
                "noRole", "123Address", "999", 20, "Yesterday",
                "Today", bloodType.toString());
        Donation donation = new Donation(donor, bloodBank, 1);
        System.out.println(donation);
    }



}
