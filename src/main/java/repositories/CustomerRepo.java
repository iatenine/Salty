package repositories;

import ORM.PepperORM;
import models.Customer;
import models.CustomerData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class CustomerRepo extends SaltyRepo<Customer> {
    String tableName = "customers";

    @Override
    public Customer save(Customer customer) {
        int id = customer.getId();
        if(exists(customer.getId())){
            id = PepperORM.addRow(tableName, customer.getCustomer_name(), customer.getCustomer_data());
        }
        else {
            HashMap<String, Object> newCols = new HashMap<>();
            newCols.put("customer_name", customer.getCustomer_name());
            newCols.put("customer_data_id", customer.getCustomer_data().getId());

            PepperORM.updateRow(tableName, customer.getId(), newCols);
        }
        return new Customer(id, customer.getCustomer_name(), customer.getCustomer_data());
    }

    @Override
    public Customer getById(int id) throws SQLException {
        ResultSet rs = PepperORM.getRow(tableName, id, allCols);
        rs.next();
        String customer_name = rs.getString("customer_name");
        int data_id = rs.getInt("customer_data_id");
        return new Customer(id,
                customer_name,
                new CustomerData(
                        data_id,
                        "",
                        ""
                ));
    }

    @Override
    public LinkedList<Customer> getAll() throws SQLException {
        LinkedList<Customer> list = new LinkedList<>();
        ResultSet rs = PepperORM.getRows(tableName, allCols);

        while(rs.next()){
            int id = rs.getInt("id");
            String customer_name = rs.getString("customer_name");
            int data_id = rs.getInt("customer_data_id");
            Customer customer = new Customer(id, customer_name, new CustomerData(data_id, "", ""));
            list.add(customer);
        }

        return list;
    }

}
