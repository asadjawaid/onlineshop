package menu.menutypes;

import configs.ApplicationContext;
import menu.Menu;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {
    private final ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if (context.getLoggedInUser() == null) {
            System.out.println("Please, log in or create a new account to change your account settings.");
            context.getMainMenu().start();
            return;
        }

        printMenuHeader();
        changeEmail();
        context.getMainMenu().start();
    }

    public void changeEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        context.getLoggedInUser().setEmail(newEmail); // updates the email for the current logged in user
        System.out.println("Your email has been successfully changed!");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHANGE EMAIL *****");
    }
}
