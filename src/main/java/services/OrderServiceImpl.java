package services;

import models.Order;
import repositories.OrderRepo;

import java.sql.SQLException;
import java.util.LinkedList;

public class OrderServiceImpl implements OrderService {

    OrderRepo or;

        public OrderServiceImpl(OrderRepo or) {
        this.or = or;
        }

    @Override
    public Order saveOrder(Order o) {
        return or.save(o);
    }

    @Override
    public Order getOrderById(int id) throws SQLException{
            return or.getById(id);
    }

    @Override
    public LinkedList<Order> getOrders() throws SQLException {
        LinkedList<Order> list = or.getAll();

        return list;

    }

    @Override
    public boolean deleteOrder(int id) {
        return or.delete(id, "orders");
    }
}
