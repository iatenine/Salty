package services;

import models.Customer;
import models.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item i);
    Item getItemById(int id);
    List<Item> getItems();
    boolean delete(int id);
}
