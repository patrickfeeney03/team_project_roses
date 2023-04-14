package ie.atu;

public class UserManagerTest {
    public static void main(String[] args) {
        UserManager userManager  = new UserManager();

        User userTest = new User(6, "gmail@gmail.com", "goodPassword", "notMyName", "admin", "Galway",
                "999333222", 19);
        boolean addUserResult = userManager.addUser(userTest);
        System.out.println("addUser result: " + addUserResult);


        userTest.setUser_Age(20);
        userTest.setUser_email("notGmail@gmail.com");
        boolean updateUserResult = userManager.updateUser(userTest);
        System.out.println("updateUser result: " + updateUserResult);
    }
}
