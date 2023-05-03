package ie.atu;

public class testInterface{
    public static void main(String[] args) {
        User user = new User();
        user.setUser_name("Name1");
        user.setUser_phone("99966969");
        user.printNamePlusPhoneNumber();

        Donor donor = new Donor();
        donor.setPatient_firstName("Sean");
        donor.setPatient_lastName("Conroy");
        donor.setPatient_phone("9999999");
        donor.printNamePlusPhoneNumber();

        Recipient recipient = new Recipient();
        recipient.setPatient_firstName("Koobs");
        recipient.setPatient_lastName("last email");
        recipient.setPatient_phone("92929929");
        recipient.printNamePlusPhoneNumber();

    }
}
