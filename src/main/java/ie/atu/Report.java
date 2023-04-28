package ie.atu;

public class Report {


    public void Donor_Report(){
        System.out.println();
        BloodType bloodType = new BloodType("A",'+');
        Donor donor = new Donor(0, "Mikaela", "Diaz",
                20, "08/08/2003", "mikaelEmail", "addressMikaela",
                "123345123", "9785684834", bloodType);


    }
    public void Recipient_Report(){
        BloodType bloodType = new BloodType("A",'+');
        Recipient recipient = new Recipient(0, "Mikaela", "Diaz",
                20, "08/08/2003", "mikaelEmail", "addressMikaela",
                "123345123", "9785684834", bloodType);

    }
}
