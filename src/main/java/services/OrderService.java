package services;

import models.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order o);
    Order getOrderById(int id);
    List<Order> getOrders();
    boolean deleteOrder(int id);
}
