package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Customer customer = new Customer(3, "Dale");
    int id = 1;
    long currTime = System.currentTimeMillis();
    Order order = new Order(id, customer, currTime);

    @Test
    void getId() {
        assertEquals(id, order.getId());
    }

    @Test
    void getCustomer() {
        assertEquals(customer, order.getCustomer());
        assertNotEquals(new Customer(4, "Peggy"), order.getCustomer());
    }

    @Test
    void getDate() {
        assertEquals(currTime, order.getDate());
    }
}