package menu.menutypes;

import configs.ApplicationContext;
import entities.Order;
import menu.Menu;
import services.OrderManagementService;
import services.impl.DefaultOrderManagementService;

import java.util.List;

/**
 * This class is responsible for managing and allowing a user to view all of their previous orders.
 */
public class MyOrdersMenu implements Menu {
    private final ApplicationContext context;
    private final OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    /**
     * Shows the current logged in user their orders
     */
    @Override
    public void start() {
        // The user must be logged in order to view their previous purchases.
        if (context.getLoggedInUser() == null) {
            System.out.println("Please log in or create a new account to see the list of your orders.");
        } else {
            // Check if the user has any previous orders
            if (orderManagementService.getOrders().isEmpty()) {
                System.out.println("Unfortunately, you do not have orders at the moment. Please make a purchase.");
            } else {
                printUserOrders();
            }
        }

        context.getMainMenu().start(); // navigate back to the main menu
    }

    /**
     * Prints out all the orders for the current logged in user
     */
    private void printUserOrders() {
        List<Order> orders = orderManagementService.getOrders();

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    /**
     * Prints the menu header
     */
    @Override
    public void printMenuHeader() {
        System.out.println("***** MY ORDERS *****");
    }
}
