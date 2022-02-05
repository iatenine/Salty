package services;

import ORM.PepperORM;
import models.Customer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer c);
    Customer getCustomerById(int id) throws SQLException;
    LinkedList<Customer> getCustomers() throws SQLException;
    boolean deleteCustomer(int id);
}
