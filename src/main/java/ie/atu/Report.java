package ie.atu;

public class Report {


    public void Donor_Report(){
        String selectSQL = "SELECT * FROM donor_info WHERE donorID = ?";

        BloodType bloodType = new BloodType("A",'+');
        Donor donor = new Donor(0, "Mikaela", "Diaz",
                20, "08/08/2003", "mikaelEmail", "addressMikaela",
                "123345123", "9785684834", bloodType);

        System.out.println("name");
        System.out.println("email");
        System.out.println("phonenumber\n");

        System.out.println("units:");
        System.out.println("Bloodtype");

    }
    public void Recipient_Report(){
        BloodType bloodType = new BloodType("A",'+');
        Recipient recipient = new Recipient(0, "Mikaela", "Diaz",
                20, "08/08/2003", "mikaelEmail", "addressMikaela",
                "123345123", "9785684834", bloodType);
        System.out.println("name");
        System.out.println("email");
        System.out.println("phonenumber\n");

        System.out.println("units:");
        System.out.println("Blood");

    }
}
