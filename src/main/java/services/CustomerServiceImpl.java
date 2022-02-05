package services;

import models.Customer;
import models.CustomerData;
import repositories.CustomerDataRepo;
import repositories.CustomerRepo;

import java.sql.SQLException;
import java.util.LinkedList;

public class CustomerServiceImpl implements CustomerService{
    CustomerRepo cr;
    CustomerDataRepo cdr;

        public CustomerServiceImpl(CustomerRepo cr, CustomerDataRepo cdr) {
            this.cr = cr;
            this.cdr = cdr;
        }

    @Override
    public Customer saveCustomer(Customer c) {
        return cr.save(c);
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        Customer c = cr.getById(id);
        return populateCustomerData(c);
    }

    @Override
    public LinkedList<Customer> getCustomers() throws SQLException {
        LinkedList<Customer> list = cr.getAll();
        LinkedList<Customer> ret = new LinkedList<>();
        for(Customer c : list){
               ret.add(populateCustomerData(c));
        }
        return ret;
    }

    @Override
    public boolean deleteCustomer(int id) {
        return cr.delete(id);
    }

    private Customer populateCustomerData(Customer c) throws SQLException {
        CustomerData cd = cdr.getById(c.getCustomer_data().getId());
        return new Customer(c.getId(), c.getCustomer_name(), cd);
    }
}
