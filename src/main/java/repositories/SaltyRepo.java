package repositories;

import ORM.PepperORM;
import models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public abstract class SaltyRepo<T> {
    String tableName;
    String[] allCols = {"*"};

    public abstract T save(T t);
    public abstract T getById(int id) throws SQLException;

    public abstract LinkedList<T> getAll() throws SQLException;
    public boolean delete(int id){
        ResultSet rs = PepperORM.deleteRow(tableName, id);
        return null == rs;
    }
    protected boolean exists(int t_id){
        ResultSet rs = PepperORM.getRow(tableName, t_id, allCols);
        return null == rs;
    }
}
