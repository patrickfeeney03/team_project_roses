package ie.atu;

import java.util.Scanner;

public class UserManagerTest {
    public static void main(String[] args) {
        UserManager userManager  = new UserManager();
        // Create the User objects that are going to be used for testing
        User userTest = new User(6, "gmail@gmail.com", "goodPassword", "notMyName", "admin", "Galway",
                "999333222", 19);

        // Test for addUser()
        boolean addUserResult = userManager.addUser(userTest);
        System.out.println("addUser result: " + addUserResult);

        // Test for updateUser()
        userTest.setUser_age(20);
        userTest.setUser_email("notGmail@gmail.com");
        boolean updateUserResult = userManager.updateUser(userTest);
        System.out.println("updateUser result: " + updateUserResult);

        // Test for removeUser()
        boolean removeUserResult = userManager.removeUser(userTest);
        System.out.println("removeUser result: " + removeUserResult);

        // Test for getUserByEmail()
        User userResult = userManager.getUserByEmail("paulA@gmail.com");
        System.out.println("getUserByEmail result (id): " + userResult.getUser_Id());

        // Test for login(), user input is required on this case, for now...
        userManager.login(new Scanner(System.in));
    }
}
