package ie.atu;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ReportTest {

    public static void main(String[] args) {
        BloodType bloodType = new BloodType("A",'+');
        Donor donor = new Donor(0, "Mikaela", "Diaz",
                20, "08/08/2003", "mikaelEmail", "addressMikaela",
                "123345123", "9785684834", bloodType);
        JFrame frame = new JFrame("Print Statement Border Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel nameLabel = new JLabel("Name"+donor.getPatient_firstName()+" "+ donor.getPatient_lastName());
        JLabel emailLabel = new JLabel("Email"+donor.getPatient_email());
        JLabel phoneLabel = new JLabel("Phone number"+donor.getPatient_phone());

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(emailLabel, BorderLayout.CENTER);
        panel.add(phoneLabel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // Print statements inside the border
        System.out.println("Name: John Doe");
        System.out.println("Email: johndoe@example.com");
        System.out.println("Phone number: 123-456-7890");
    }
}
