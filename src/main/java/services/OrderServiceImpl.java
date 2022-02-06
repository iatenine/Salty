package services;

import models.Order;
import repositories.CustomerDataRepo;
import repositories.CustomerRepo;
import repositories.OrderRepo;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        return or.delete(id);
    }
}
