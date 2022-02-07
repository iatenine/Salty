package models;

import ORM.PepperORM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    // Pepper Demo Test
//    public static void main(String[] args) {
//        PepperORM.dropTable("customers_orders");
//        PepperORM.create1ToManyRelationship("customers", "orders");
//        PepperORM.createManyToManyRelationship("orders", "items");
//    }

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