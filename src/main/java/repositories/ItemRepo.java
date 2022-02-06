package repositories;

import ORM.PepperORM;
import models.CustomerData;
import models.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class ItemRepo extends SaltyRepo<Item>{
    String tableName = "items";

    @Override
    public Item save(Item item) throws SQLException {
        // Update only if already exists
        if(exists(item.getId(), tableName)){
            HashMap<String, Object> colmuns = new HashMap<>();
            colmuns.put("name", item.getName());
            colmuns.put("price", item.getPrice());
            colmuns.put("available", item.isAvailable());
            ResultSet rs = PepperORM.updateRow(tableName, item.getId(), colmuns);
            return buildItem(rs);
        }
        int id = PepperORM.addRow(tableName, item.getName(), item.getPrice(), item.isAvailable());
        return new Item(id, item.getName(), item.getPrice(), item.isAvailable());
    }

    @Override
    public Item getById(int id) throws SQLException {
        ResultSet rs = PepperORM.getRow(tableName, id, allCols);
        return buildItem(rs);
    }

    @Override
    public LinkedList<Item> getAll() throws SQLException {
        LinkedList<Item> list = new LinkedList<>();
        ResultSet rs = PepperORM.getRows(tableName, allCols);
        while(rs.next()){
            Item item = new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getBoolean("available")
            );
            list.add(item);
        }

        return list;
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
