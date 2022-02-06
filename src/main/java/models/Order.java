package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}