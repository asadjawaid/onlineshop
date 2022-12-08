package configs;

import entities.Cart;
import entities.User;
import entities.impl.DefaultCart;
import menu.Menu;

/**
 * Following singleton design pattern to have only one instance of the Application Context.
 * Will be used to manage the flow of the application
 */
public class ApplicationContext {
    private static ApplicationContext instance = null;

    private User loggedInUser;
    private Menu mainMenu;
    private Cart sessionCart;

    /**
     * Private default constructor required to implement singleton
     */
    private ApplicationContext() { }

    /**
     * Using lazy initialization
     * @return a new instance of the ApplicationContext
     */
    public static ApplicationContext getInstance() {
        if (instance == null) instance = new ApplicationContext();

        return instance;
    }

    public void setLoggedInUser(User user) {
        if (this.sessionCart != null) {
            this.sessionCart.clear(); // we have to clear the session cart when a new user logs into the application
        }

        this.loggedInUser = user;
    }

    /**
     *
     * @return the current logged in user
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     *
     * @return the main menu
     */
    public Menu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Cart getSessionCart() {
        if (sessionCart == null) sessionCart = new DefaultCart();
        return sessionCart;
    }

    public void setSessionCart(Cart sessionCart) {
        this.sessionCart = sessionCart;
    }
}
