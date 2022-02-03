package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    int id = 1;
    String name = "Diane";
    int fk_id = 4;
    Customer customer = new Customer(id, name, fk_id);

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
        assertEquals(fk_id, customer.getCustomer_data_id());
    }
}