package entities.impl;

import entities.Order;
import entities.Product;

import java.util.List;

public class DefaultOrder implements Order {
    private static final int CARD_AMOUNT_OF_DIGITS = 16;

    // the credit card number used
    private String creditCardNumber;
    // the list of products purchased
    private List<Product> products;
    // The person that made the order
    private int customerId;

    public DefaultOrder() {}

    /**
     *
     * @param userInput is the user's credit card number
     * @return true if the card is valid and vice versa.
     */
    @Override
    public boolean isCreditCardNumberValid(String userInput) {
        return userInput.trim().length() == CARD_AMOUNT_OF_DIGITS;
    }

    /**
     * Update the credit card number
     * @param creditCardNumber is the new credit card number
     */
    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (isCreditCardNumberValid(creditCardNumber))
            this.creditCardNumber = creditCardNumber;
        else
            System.out.println("Invalid credit card number!");
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "DefaultOrder{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", products=" + products +
                ", customerId=" + customerId +
                '}';
    }
}
