package ie.atu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateTest {


        public static void main(String[] args) {
            BloodType bloodtype = new BloodType("A",'+');
            BloodUnit bloodUnit = new BloodUnit(bloodtype);
            System.out.println(bloodUnit);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDate.format(formatter);
            System.out.println("Current date: " + formattedDate);
        }


}
