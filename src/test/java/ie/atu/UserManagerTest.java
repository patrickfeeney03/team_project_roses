package ie.atu;

public class UserManagerTest {
    public static void main(String[] args) {
        UserManager userManager  = new UserManager();

        User userTest = new User(6, "gmail@gmail.com", "goodPassword", "notMyName", "admin", "Galway",
                "999333222", 19);
        boolean addUserResult = userManager.addUser(userTest);
        System.out.println("addUser result: " + addUserResult);
    }
}
