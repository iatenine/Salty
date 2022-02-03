package models;

/**
 * Order
 * Model representing orders table in db
 * Represents a single order placed by a customer, referenced by customer_id
 * Items are linked via a junction table
 */

public class Order {
    final private int id;
    final private int customer_id;
    final private long date;

    public int getId() {
        return id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public long getDate() {
        return date;
    }

    public Order(int id, int customer_id, long date) {
        this.id = id;
        this.customer_id = customer_id;
        this.date = date;
    }
}
