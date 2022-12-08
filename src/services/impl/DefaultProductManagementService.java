package services.impl;

import entities.Product;
import entities.impl.DefaultProduct;
import services.ProductManagementService;

import java.util.Arrays;
import java.util.List;

/**
 * We are going to have only one instance during the execution of the program
 */
public class DefaultProductManagementService implements ProductManagementService {
    private static List<Product> products = null;
    private static DefaultProductManagementService instance = null;

    // Gets called only once for each instance of DefaultProductManagementService (we'll only have one).
    // We are also initializing our products here.
    // Will get called before the execution of the main program.
    static {
        initProducts();
    }

    /**
     * This routine is used to initialize our products.
     */
    private static void initProducts() {
        products = Arrays.asList(
                new DefaultProduct(1, "Hardwood Oak Suffolk Internal Door", "Doors", 109.99),
                new DefaultProduct(2, "Oregon Cottage Interior Oak Door", "Doors", 179.99),
                new DefaultProduct(3, "Oregon Cottage Horizontal Interior White Oak Door", "Doors", 189.99),
                new DefaultProduct(4, "4 Panel Oak Deco Interior Door", "Doors", 209.09),
                new DefaultProduct(5, "Worcester 2000 30kW Ng Combi Boiler Includes Free Comfort+ II controller", "Boilers", 989.99),
                new DefaultProduct(6, "Glow-worm Betacom 4 30kW Combi Gas Boiler ERP", "Boilers", 787.99),
                new DefaultProduct(7, "Worcester 2000 25kW Ng Combi Boiler with Free Comfort+ II controller", "Boilers", 859.99),
                new DefaultProduct(8, "Wienerberger Terca Class B Engineering Brick Red 215mm x 102.5mm x 65mm (Pack of 504)", "Bricks", 402.99),
                new DefaultProduct(9, "Wienerberger Terca Engineering Brick Blue Perforated Class B 65mm (Pack of 400)", "Bricks", 659.99),
                new DefaultProduct(10, "Wienerberger Engineering Brick Red Smooth Class B 73mm - Pack of 368", "Bricks", 523.99));
    }

    private DefaultProductManagementService() {}

    /**
     * Using Lazy initialization to return an instance of this class.
     * @return a single instance of the DefaultProductManagementService class.
     */
    public static ProductManagementService getInstance() {
        // If the instance has not been created then create it.
        if (instance == null) instance = new DefaultProductManagementService();

        return instance;
    }

    /**
     *
     * @return all of the products
     */
    @Override
    public List<Product> getProducts() {
        return products;
    }

    /**
     *
     * @param productId is the id of that product.
     * @return the product to be found
     */
    @Override
    public Product getProductById(int productId) {
        for (Product currentProduct : products) {
            if (currentProduct.getProductId() == productId) {
                return currentProduct;
            }
        }
        return null;
    }
}
