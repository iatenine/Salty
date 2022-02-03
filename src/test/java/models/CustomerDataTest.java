package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataTest {

    CustomerData customerData = new CustomerData(3, "321-867-5309",
            "123 Elm St,\nAnywhere, USA");

    @Test
    void getId() {
        assertEquals(3, customerData.getId());
    }

    @Test
    void getPhone() {
        assertEquals("321-867-5309", customerData.getPhone());
    }

    @Test
    void getAddress() {
        assertEquals("123 Elm St,\nAnywhere, USA", customerData.getAddress());
    }
}