package services;

import entities.Product;

import java.util.List;

/**
 * This interface contains a set of behaviors for managing our Products
 */
public interface ProductManagementService {
    List<Product> getProducts();

    Product getProductById(int productIdToAddToCart);
}
