package repositories;

import ORM.PepperORM;
import models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public abstract class SaltyRepo<T> {
    String[] allCols = {"*"};

    public abstract T save(T t) throws SQLException;
    public abstract T getById(int id) throws SQLException;

    public abstract LinkedList<T> getAll() throws SQLException;
    public boolean delete(int id, String tableName){
        ResultSet rs = PepperORM.deleteRow(tableName, id);
        return null == rs;
    }
    public boolean exists(int t_id, String tableName) {
        ResultSet rs = PepperORM.getRow(tableName, t_id, allCols);
        try {
            if(rs == null || !rs.next())
                return false;
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
