package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CustomerData
 * Model representing db table that holds sensitive data about a customer but decoupled from their name
 */
@AllArgsConstructor
@Getter
public class CustomerData {
    final private int id;
    final private String phone;
    final private String address;

    public CustomerData(String phone, String address){
        this.id = 0;
        this.phone = phone;
        this.address = address;
    }
}
