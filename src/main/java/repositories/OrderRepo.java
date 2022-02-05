package repositories;

import models.Order;

import java.sql.SQLException;
import java.util.LinkedList;

public class OrderRepo extends SaltyRepo<Order>{

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Order getById(int id) throws SQLException {
        return null;
    }

    @Override
    public LinkedList<Order> getAll() throws SQLException {
        return null;
    }
}
