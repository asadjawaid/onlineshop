package entities;

import java.util.List;

public interface Order {
    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String creditCardNumber);

    void setProducts(List<Product> products);

    void setCustomerId(int customerId);

    int getCustomerId();
}
