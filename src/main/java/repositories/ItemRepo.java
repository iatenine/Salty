package repositories;

import models.Item;

import java.sql.SQLException;
import java.util.LinkedList;

public class ItemRepo extends SaltyRepo<Item>{

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public Item getById(int id) throws SQLException {
        return null;
    }

    @Override
    public LinkedList<Item> getAll() throws SQLException {
        return null;
    }
}
