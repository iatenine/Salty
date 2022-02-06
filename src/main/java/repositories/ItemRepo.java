package repositories;

import models.CustomerData;
import models.Item;

import java.sql.ResultSet;
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

    public Item buildItem(ResultSet rs) throws SQLException {
        rs.next();
        return new Item(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getBoolean("available")
        );
    }
}
