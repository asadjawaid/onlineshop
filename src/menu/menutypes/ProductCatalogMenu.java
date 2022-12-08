package menu.menutypes;

import java.util.Scanner;

import configs.ApplicationContext;
import entities.Cart;
import entities.Order;
import entities.Product;
import entities.impl.DefaultOrder;
import menu.Menu;
import services.ProductManagementService;
import services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {
    private ProductManagementService productManagementService;
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu menuToNavigateTo;

        while (true) {
            printMenuHeader();
            printProducts();

            System.out.print("Enter: ");
            String input = getUserInput();

            // Navigates back to the main menu
            if (input.equalsIgnoreCase("menu")) {
                menuToNavigateTo = new MainMenu();
                break;
            }

            // If the user is not logged in, then they cannot add a product to their cart.
            if (context.getLoggedInUser() == null) {
                System.out.println("You are not logged in! Please sign in or create an account.");
                menuToNavigateTo = new MainMenu();
                break;
            }

            // checkout
            if (input.equalsIgnoreCase("checkout")) {
                Cart cart = context.getSessionCart();

                // Checks if the cart is empty
                if (cart.isEmpty()) {
                    System.out.println("You cart is empty. Please, add a product to your cart first and then proceed with checkout.");
                    continue;
                }

                menuToNavigateTo = new CheckoutMenu();
                break;
            } else {
                // find the product we want to add
                Product product = getProduct(Integer.parseInt(input));

                // Product does not exist
                if (product == null) {
                    System.out.println("Please, provide a valid product ID if you want to add a product to your cart. Or enter ‘checkout’ " +
                            "if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu.");
                    continue; // restart the loop
                }

                // Add the product.
                addProductToCart(product);
            }
        }

        menuToNavigateTo.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("***** PRODUCT CATALOG *****");
        System.out.println("Enter a product's id to add it to the cart or 'menu' if you want to navigate back to the main menu");
    }

    /**
     * This helper method prints all of the products to the console.
     */
    private void printProducts() {
        System.out.println("Our products:");
        for (Product product : productManagementService.getProducts()) {
            System.out.println(product);
        }
    }

    /**
     * This method is used to get user input
     * @return user input
     */
    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * This helper method is designed to fetch with the provided product id.
     * @param productId is the id used to find the product
     * @return a product or null (does not exist).
     */
    private Product getProduct(int productId) {
        return productManagementService.getProductById(productId);
    }

    /**
     * This method is used to add a product to current user's cart.
     * @param product is the product to be added to the cart.
     */
    private void addProductToCart(Product product) {
        // Add the product to the logged in user's cart.
        context.getSessionCart().addProduct(product);
        System.out.println(product.getProductName() + " has been added to your cart. If you want to add to add a new product" +
                " enter the product id. If you want to proceed with checkout - enter 'checkout'.");
    }
}
