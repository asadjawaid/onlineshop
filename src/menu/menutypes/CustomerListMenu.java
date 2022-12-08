package menu.menutypes;

import configs.ApplicationContext;
import entities.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.List;

public class CustomerListMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    /**
     * This method is responsible for printing out all of the customers
     */
    @Override
    public void start() {
        // Get all the users
        List<User> users = userManagementService.getUsers();

        if (users.isEmpty()) {
            System.out.println("Unfortunately, there are no users at the moment!");
        } else {
            printMenuHeader();
            for (User user : users) {
                System.out.println(user);
            }
        }

        // navigate back to the main menu
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** USERS *****");
    }
}
