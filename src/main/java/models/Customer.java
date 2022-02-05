package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Customer
 * Model representing customer table in db. Only holds name and a foreign key to access
 * sensitive information
 */
@AllArgsConstructor
@Getter
public class Customer{
    final private int id;
    final private String customer_name;
    final private CustomerData customer_data;

    // No id constructor
    public Customer(String customer_name){
        this.id = -1;
        this.customer_name = customer_name;
        this.customer_data = null;
    }

    public Customer(int id, String customer_name){
        this.id = id;
        this.customer_name = customer_name;
        this.customer_data = null;
    }
}
