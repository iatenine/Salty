package models;

/**
 * CustomerData
 * Model representing db table that holds sensitive data about a customer but decoupled from their name
 */

public class CustomerData {
    final private int id;
    final private String phone;
    final private String address;

    public CustomerData(int id, String phone, String address) {
        this.id = id;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

}
