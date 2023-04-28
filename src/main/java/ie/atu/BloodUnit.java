package ie.atu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class BloodUnit {
    private BloodType bloodType;
    private String date;

    public BloodUnit(BloodType bloodType, String date) {
        this.bloodType = bloodType;
        this.date = date;
    }

    LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDate.format(formatter);
            System.out.println("Current date: " + formattedDate);


}
