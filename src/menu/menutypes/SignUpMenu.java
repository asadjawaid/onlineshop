package menu.menutypes;

import configs.ApplicationContext;
import entities.User;
import entities.impl.DefaultUser;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {
    private final ApplicationContext context;
    private final UserManagementService userManagementService;

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
        String firstName = capitalize(scanner.nextLine().trim());

        System.out.print("Enter your last name: ");
        String lastName = capitalize(scanner.nextLine().trim());

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        User newUser = new DefaultUser(firstName, lastName, password, email);

        String messageOutput = userManagementService.registerUser(newUser);

        // Unsuccessful, user did not get created
        if (!messageOutput.isEmpty()) {
            System.out.println(messageOutput);
        } else context.setLoggedInUser(newUser); // Sign in the user

        // Navigate back to the main menu
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN UP *****");
    }

    /**
     * This helper method capitalizes the first letter
     * @param input is the word provided to make the first letter uppercase
     * @return the new word or null.
     */
    private String capitalize(String input) {
        if (input == null) return null;

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
