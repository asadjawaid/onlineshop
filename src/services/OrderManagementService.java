package services;

import entities.Order;
import java.util.List;

public interface OrderManagementService {

    void addOrder(Order order);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrders();
}
