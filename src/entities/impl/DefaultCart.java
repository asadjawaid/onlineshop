package entities.impl;

import entities.Cart;
import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {
    // A cart has a list the products
    private List<Product> products;

    public DefaultCart() {
        this.products = new ArrayList<>();
    }

    /**
     * Checks if the cart is empty of not.
     * @return true or false depending on the size of the list.
     */
    @Override
    public boolean isEmpty() {
        return this.products.size() == 0;
    }

    /**
     * Adding a new product
     * @param product is that item that we would like to add to our cart
     */
    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * Getting all the products
     * @return all the list of products inside of this cart
     */
    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * Clear the list
     */
    @Override
    public void clear() {
        products = null;
    }
}
