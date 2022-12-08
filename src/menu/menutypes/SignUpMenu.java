package menu.menutypes;

import configs.ApplicationContext;
import entities.User;
import entities.impl.DefaultUser;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }


    /**
     * This method sign up new user to the system.
     */
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        printMenuHeader();

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine().trim();

        User newUser = new DefaultUser(firstName, lastName, password, email);

        String messageOutput = userManagementService.registerUser(newUser);

        // Unsuccessful, user did not get created
        if (!messageOutput.isEmpty()) {
            System.out.println(messageOutput);
        } else context.setLoggedInUser(newUser); // Sign in the user

        scanner.close();

        // Navigate back to the main menu
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN UP *****");
    }
}
