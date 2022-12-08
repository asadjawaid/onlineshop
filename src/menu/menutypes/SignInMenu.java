package menu.menutypes;

import configs.ApplicationContext;
import entities.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {
    private static final String WELCOME_MESSAGE = "Glad to see you back ";
    private static final String ERROR_MESSAGE = "Unfortunately, such login and password doesn't exist!";

    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    /**
     * This method signs in a user.
     * We will assume a user is logged in
     */
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        printMenuHeader();

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        User newUser = userManagementService.getUserByEmail(email);

        // Validating user
        if (newUser != null && newUser.getPassword().equals(password)) {
            System.out.println(WELCOME_MESSAGE + " " + newUser.getFirstName() + " " + newUser.getLastName());
            context.setLoggedInUser(newUser); // log in the user
        } else
            System.out.println(ERROR_MESSAGE);

        // navigate back to the main menu
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** SIGN IN *****");
    }
}

