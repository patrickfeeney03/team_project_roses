package ie.atu;

public class TestDonationDates {
    public static void main(String[] args) {
        System.out.println(PatientManager.getFirstDonation(2));

        System.out.println(PatientManager.setFirstDonation(2, "1990-01-01"));

        System.out.println(PatientManager.getFirstDonation(2));
    }
}
