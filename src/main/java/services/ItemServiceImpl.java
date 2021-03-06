package services;

import models.Item;
import repositories.ItemRepo;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemServiceImpl implements ItemService{

    final private ItemRepo repo;

    public ItemServiceImpl(ItemRepo repo){
        this.repo = repo;
    }

    @Override
    public Item saveItem(Item i) throws SQLException {
        return repo.save(i);
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        return repo.getById(id);
    }

    @Override
    public LinkedList<Item> getItems() throws SQLException {
        return repo.getAll();
    }

    @Override
    public boolean delete(int id) {
        return repo.delete(id, "items");
    }
}
