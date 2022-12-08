package entities;
import java.util.List;
/**
 * Interface that contains behaviors for a cart
 */
public interface Cart {
    /**
     * This method checks whether the current cart is empty
     * @return true or false
     */
    boolean isEmpty();

    /**
     * This method adds a new product to this cart
     * @param product is that item that we would like to add to our cart
     */
    void addProduct(Product product);

    /**
     *
     * @return the list of all products
     */
    List<Product> getProducts();

    /**
     * Clears the cart
     */
    void clear();
}
