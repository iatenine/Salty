package repositories;

import ORM.PepperORM;
import models.Customer;
import models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class OrderRepo extends SaltyRepo<Order> {

    @Override
    public Order save(Order order) {

        int id = order.getId();
        if (exists(order.getId())) {
            id = PepperORM.addRow(tableName, order.getId(), order.getCustomer(), order.getDate());
        } else {
            HashMap<String, Object> newCols = new HashMap<>();
            newCols.put("order_customer", order.getCustomer());
            newCols.put("order_id", order.getId());

            PepperORM.updateRow(tableName, order.getId(), newCols);
        }
        return new Order(id, order.getCustomer(), order.getDate());
    }

    @Override
    public Order getById(int id) throws SQLException {

        ResultSet rs = PepperORM.getRow(tableName, id, allCols);
        rs.next();
        Customer customer = (Customer) rs.getObject("customer");
        long date = rs.getLong("date");
        return new Order(id, customer, date);
    }

    @Override
    public LinkedList<Order> getAll() throws SQLException {


        LinkedList<Order> list = new LinkedList<>();
        ResultSet rs = PepperORM.getRows(tableName, allCols);

        while (rs.next()) {
            int id = rs.getInt("id");
            Customer customer = (Customer) rs.getObject("customer");
            long date = rs.getLong("date");
            Order order = new Order(id, customer, date);
            list.add(order);
        }

        return list;

    }
}