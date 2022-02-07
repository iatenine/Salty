package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Order
 * Model representing orders table in db
 * Represents a single order placed by a customer, referenced by customer_id
 * Items are linked via a junction table
 */
@Getter
@AllArgsConstructor
public class Order {
    final private int id;
    final Customer customer;
    final private long date;
    private LinkedList<Item> items;

    public Order(int id, Customer customer, long date){
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.items = new LinkedList<>();
    }

    public Order(Customer customer, long date){
        id = 0;
        this.customer = customer;
        this.date = date;
        this.items = new LinkedList<>();
    }

    public Order(Customer customer, long date, LinkedList<Item> items){
        id = 0;
        this.customer = customer;
        this.date = date;
        this.items = items;
    }

    public String getDateString(){
        return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }
}