package menu.menutypes;

import configs.ApplicationContext;
import menu.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {
    // Constants:
    public static final String MENU_COMMAND = "menu";
    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";


    // Instance variables
    private final ApplicationContext context;

    // Will get called everytime an instance of a MainMenu gets created.
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if (context.getMainMenu() == null) {
            context.setMainMenu(this);
        }

        Menu menuToNavigate;
        Scanner scanner = new Scanner(System.in);

        mainLoop:
        while (true) {
            printMenuHeader();

            System.out.println("User input: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Have a great day!");
                System.exit(0);
            } else {
                int option = Integer.parseInt(userInput);

                switch (option) {
                    // Sign up menu
                    case 1:
                        menuToNavigate = new SignUpMenu();
                        break mainLoop;

                    // Sign in or sign out, depending on if their is a user that is already logged in.
                    case 2:
                        if (context.getLoggedInUser() == null) {
                            menuToNavigate = new SignInMenu();
                        } else
                            menuToNavigate = new SignOutMenu();

                        break mainLoop;

                    // View all the products
                    case 3:
                        menuToNavigate = new ProductCatalogMenu();
                        break mainLoop;

                    // View orders
                    case 4:
                        menuToNavigate = new MyOrdersMenu();
                        break mainLoop;

                    // Used to update settings for a logged in user
                    case 5:
                        menuToNavigate = new SettingsMenu();
                        break mainLoop;

                    // View all customers (does not show passwords).
                    case 6:
                        menuToNavigate = new CustomerListMenu();
                        break mainLoop;
                    default:
                        System.out.println("Invalid option selected. Please enter 1, 2, 3, 4, or 5.");
                        break;
                }
            }
        }

        scanner.close();
        menuToNavigate.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** MAIN MENU *****");
        // User is not logged in
        if (context.getLoggedInUser() == null) {
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        } else System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
    }
}
