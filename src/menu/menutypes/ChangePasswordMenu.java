package menu.menutypes;

import configs.ApplicationContext;
import menu.Menu;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {
    private final ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    /**
     * This function
     */
    @Override
    public void start() {
        printMenuHeader();
        changePassword();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHANGE PASSWORD ****");
    }

    /**
     * Changes a logged in user's password
     */
    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        scanner.close();

        System.out.println("Your password has been successfully changed.");
        context.getLoggedInUser().setPassword(newPassword); // set the new password
    }
}
