package menu.menutypes;

import configs.ApplicationContext;
import entities.Cart;
import entities.Order;
import entities.Product;
import entities.impl.DefaultOrder;
import menu.Menu;
import services.OrderManagementService;
import services.impl.DefaultOrderManagementService;

import java.util.List;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private final OrderManagementService orderManagementService;
    private final ApplicationContext context;

    {
        orderManagementService = DefaultOrderManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    /**
     * The start method calls the processCheckout method to process the payment for the product(s) inside of the cart
     * and the navigates back to the main menu
     */
    @Override
    public void start() {
        processCheckout();
        context.getMainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** CHECKOUT *****");
    }

    /**
     * This method will process the payment and validate to see if a credit card is valid or not. If the user has
     * entered an invalid credit card, they have once more chance. If the card is validated and correct then we create
     * a new order for the current logged in user.
     */
    private void processCheckout() {
        Scanner scanner = new Scanner(System.in);
        int tries = 2;

        while (tries != 0) {
            printMenuHeader();
            // Ask the user for their credit card info and press enter key to confirm their purchase
            System.out.print("Enter your credit card number (16 digits and no spaces): ");
            String creditCardNumber = scanner.nextLine();

            System.out.print("Confirm by pressing the 'enter' key: ");
            scanner.nextLine();

            // If the order could not be created (invalid credit card number) ask the user to try again.
            if (!createOrder(creditCardNumber)) {
                System.out.println("You entered an invalid credit card number. Valid credit cards should contain 16 digits. Please try one more time!");
                tries--;
            } else {
                System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
                context.getSessionCart().clear(); // clear the cart
                break;
            }
        }
    }

    /**
     * This method creates a new order
     * @param creditCardNumber is the credit card number used to make the purchase
     * @return true if the order was successfully created or vice versa.
     */
    private boolean createOrder(String creditCardNumber) {
        Order order = new DefaultOrder();

        // If the card is not valid then return false
        if (!order.isCreditCardNumberValid(creditCardNumber)) return false;

        order.setCreditCardNumber(creditCardNumber);
        order.setCustomerId(context.getLoggedInUser().getUserId());
        order.setProducts(context.getSessionCart().getProducts());

        orderManagementService.addOrder(order);

        return true;
    }
}
