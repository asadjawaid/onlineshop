package menu.menutypes;

import configs.ApplicationContext;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;
import java.util.Scanner;

public class SettingsMenu implements Menu {
    private final ApplicationContext context;
    private final UserManagementService userManagementService;
    private final Scanner scanner = new Scanner(System.in);

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    /**
     * This method is responsible for allowing the user to change their password or email.
     */
    @Override
    public void start() {
        Menu menuToNavigateTo = null;
        int tries = 2;

        if (context.getLoggedInUser() == null) {
            System.out.println("Please, log in or create new account to change your account settings");
            context.getMainMenu().start();
            return;
        }

        mainLoop:
        while (tries != 0) {
            String option = selectOption();
            switch (option) {
                case "1": {
                    menuToNavigateTo = new ChangePasswordMenu();
                    break mainLoop;
                }
                case "2": {
                    menuToNavigateTo = new ChangeEmailMenu();
                    break mainLoop;
                }
                case "menu": {
                    menuToNavigateTo = context.getMainMenu();
                    break mainLoop;
                }
                default:
                    System.out.println("Invalid option! Please enter 1 or 2. Try one more time or type 'menu' to go back to the main menu.");
                    tries--;
            }
        }

        if (tries == 0) menuToNavigateTo = context.getMainMenu();

        menuToNavigateTo.start();
    }

    /**
     *
     * @return the user's option
     */
    private String selectOption() {
        System.out.print("Enter 1 to change password or enter 2 to change email: ");
        return scanner.nextLine();
    }

    /**
     * Display the menu header
     */
    @Override
    public void printMenuHeader() {
        System.out.println("***** SETTINGS *****");
    }
}
