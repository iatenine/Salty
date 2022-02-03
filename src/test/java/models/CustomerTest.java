package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    int id = 1;
    String name = "Diane";
    CustomerData cd = new CustomerData(3, "", "");
    Customer customer = new Customer(id, name, cd);

    @Test
    void getId() {
        assertEquals(id, customer.getId());
    }

    @Test
    void getCustomer_name() {
        assertEquals(name, customer.getCustomer_name());
    }

    @Test
    void getCustomer_data_id() {
        assertEquals(cd, customer.getCustomer_data());
    }
}