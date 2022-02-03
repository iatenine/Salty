package models;

/**
 * Customer
 * Model representing customer table in db. Only holds name and a foreign key to access
 * sensitive information
 */

public class Customer {
    final private int id;
    final private String customer_name;
    final private int customer_data_id;

    public Customer(int id, String customer_name, int customer_data_id) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_data_id = customer_data_id;
    }

    public int getId() {
        return id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public int getCustomer_data_id() {
        return customer_data_id;
    }
}
