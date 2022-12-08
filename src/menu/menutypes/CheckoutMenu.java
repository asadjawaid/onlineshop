package menu.menutypes;

import configs.ApplicationContext;
import entities.Cart;
import entities.Order;
import entities.impl.DefaultOrder;
import menu.Menu;
import services.OrderManagementService;
import services.impl.DefaultOrderManagementService;

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
        processCheckout(context.getSessionCart());
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
     * @param cart to process and checkout all the products
     */
    private void processCheckout(Cart cart) {
        Scanner scanner = new Scanner(System.in);
        int tries = 2;

        while (tries != 0) {
            // Ask the user for their credit card info and press enter key to confirm their purchase
            System.out.print("Enter your credit card number (16 digits and no spaces): ");
            String creditCardNumber = scanner.nextLine();

            System.out.println("Confirm by pressing the 'enter' key: ");
            scanner.nextLine();

            Order order = new DefaultOrder();

            // Check if the credit card number provided is valid. If it is then proceed with checkout and vice versa.
            if (order.isCreditCardNumberValid(creditCardNumber)) {
                order.setCreditCardNumber(creditCardNumber); // setting the credit card number
                order.setProducts(cart.getProducts()); // setting all the products in the cart to the order list
                order.setCustomerId(context.getLoggedInUser().getUserId()); // set the customer id for this order
                orderManagementService.addOrder(order); // add the order to the list of all orders

                cart.clear(); // empty the cart
                System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
                break;
            }

            System.out.println("You entered an invalid credit card number. Valid credit cards should contain 16 digits. Please try one more time!");
            tries--;
        }
    }
}
