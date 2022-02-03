package services;

import models.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer c);
    Customer getCustomerById(int id);
    List<Customer> getCustomers();
    boolean deleteCustomer(int id);
}
