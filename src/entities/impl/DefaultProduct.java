package entities.impl;

import entities.Product;

public class DefaultProduct implements Product {
    private String productName, categoryName;
    private double price;
    private int productId;

    public DefaultProduct() {}

    /**
     * Create a new product
     * @param productId unique id that identifies a product
     * @param productName is the name of the product
     * @param categoryName is the name of category for this product
     * @param price the price for this product
     */
    public DefaultProduct(int productId, String productName, String categoryName, double price) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
        this.productId = productId;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "DefaultProduct[" +
                "productName='" + productName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", productId=" + productId +
                ']';
    }
}
