package models;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    final private int id;
    final private String name;
    final private int price;
    final private boolean available;
}
