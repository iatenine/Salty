package services;

import models.Customer;
import models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    Item saveItem(Item i);
    Item getItemById(int id) throws SQLException;
    List<Item> getItems() throws SQLException;
    boolean delete(int id);
}
