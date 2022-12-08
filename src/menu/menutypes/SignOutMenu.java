package menu.menutypes;

import configs.ApplicationContext;
import menu.Menu;

public class SignOutMenu implements Menu {
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    // the user is already logged in.
    @Override
    public void start() {
        context.setLoggedInUser(null);
        printMenuHeader();
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("**** SIGN OUT ***");
        System.out.println("Have a nice day! Looking forward to seeing you back!");
    }
}

