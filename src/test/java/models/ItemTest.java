package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    int id = 1;
    String name = "Spaghetti";
    int price = 2000;   //20.00
    boolean available = false;
    Item item = new Item(id, name, price, available);

    @Test
    void getId() {
        assertEquals(id, item.getId());
    }

    @Test
    void getName() {
        assertEquals(name, item.getName());
    }

    @Test
    void getPrice() {
        assertEquals(price, item.getPrice());
    }

    @Test
    void isAvailable() {
        assertEquals(available, item.isAvailable());
    }
}