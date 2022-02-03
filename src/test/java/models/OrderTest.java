package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    int id = 1;
    int customer_id = 2;
    long currTime = System.currentTimeMillis();
    Order order = new Order(id, customer_id, currTime);

    @Test
    void getId() {
        assertEquals(id, order.getId());
    }

    @Test
    void getCustomer_id() {
        assertEquals(customer_id, order.getCustomer_id());
    }

    @Test
    void getDate() {
        assertEquals(currTime, order.getDate());
    }
}