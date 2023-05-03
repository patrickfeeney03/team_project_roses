package ie.atu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class BloodUnit {
    private BloodType bloodType;
    private String date;
    private int bloodIDSQL;

    public BloodUnit(BloodType bloodType) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        this.bloodType = bloodType;
        this.date = formattedDate;
    }

    public BloodUnit(BloodType bloodType, int bloodIDSQL) {
        this.bloodType = bloodType;
        this.bloodIDSQL = bloodIDSQL;
    }

    public BloodUnit() {

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

    public int getBloodIDSQL() {
        return bloodIDSQL;
    }

    public void setBloodIDSQL(int bloodIDSQL) {
        this.bloodIDSQL = bloodIDSQL;
    }

    @Override
    public String toString() {
        return "BloodUnit{" +
                "bloodType=" + bloodType +
                ", date='" + date + '\'' +
                '}';
    }
}
