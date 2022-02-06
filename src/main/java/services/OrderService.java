package services;

import models.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    Order saveOrder(Order o);
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrders() throws SQLException;
    boolean deleteOrder(int id);

}
