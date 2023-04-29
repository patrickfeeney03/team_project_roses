package ie.atu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class BloodUnit {
    private BloodType bloodType;
    private String date;

    public BloodUnit(BloodType bloodType) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        System.out.println("Current date: " + formattedDate);
        this.bloodType = bloodType;
        this.date = formattedDate;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "BloodUnit{" +
                "bloodType=" + bloodType +
                ", date='" + date + '\'' +
                '}';
    }
}
