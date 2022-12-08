package services.impl;

import entities.Order;
import services.OrderManagementService;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderManagementService implements OrderManagementService {
    private static final int DEFAULT_ORDER_CAPACITY = 10;

    private static DefaultOrderManagementService instance;

    // Contains a list of all the orders.
    private static List<Order> orders;

    private DefaultOrderManagementService() {}

    /**
     * Like all of the management services, we will only have a single instance of DefaultOrderManagementService
     * and use this static methods to get the instance
     * @return an instance of DefaultOrderManagementService
     */
    public static OrderManagementService getInstance() {
        if (instance == null) instance = new DefaultOrderManagementService();

        return instance;
    }

    /**
     * Adds a new order
     * @param order is the order of the user is to be added to the list of orders
     */
    @Override
    public void addOrder(Order order) {
        if (order == null) {
            return;
        }

        orders.add(order);
    }

    /**
     * This method looks for all the orders made by userId
     * @param userId is the id number of the user
     * @return the list of orders that userId has made
     */
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> usersOrder = new ArrayList<>();

        for (Order order : orders) {
            if (order.getCustomerId() == userId) {
                usersOrder.add(order);
            }
        }

        if (usersOrder.isEmpty()) {
            return null;
        }

        return usersOrder;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }
}
