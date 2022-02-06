package repositories;


import ORM.PepperORM;
import models.CustomerData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class CustomerDataRepo extends SaltyRepo<CustomerData>{
    String tableName = "customer_data";

    @Override
    public CustomerData save(CustomerData customerData) throws SQLException {
        // Update only if already exists
        if(exists(customerData.getId())){
            HashMap<String, Object> colmuns = new HashMap<>();
            colmuns.put("phone", customerData.getPhone());
            colmuns.put("address", customerData.getAddress());
            ResultSet rs = PepperORM.updateRow(tableName, customerData.getId(), colmuns);
            rs.next();
            return buildCustomer(rs);
        }
        int id = PepperORM.addRow(tableName, customerData.getPhone(), customerData.getAddress());
        return new CustomerData(id, customerData.getPhone(), customerData.getAddress());
    }

    @Override
    public CustomerData getById(int id) throws SQLException {
        ResultSet rs = PepperORM.getRow(tableName, id, allCols);
        return buildCustomer(rs);
    }

    @Override
    public LinkedList<CustomerData> getAll() throws SQLException {
        LinkedList<CustomerData> list = new LinkedList<>();
        ResultSet rs = PepperORM.getRows(tableName, allCols);
        while(rs.next()){
            list.add(buildCustomer(rs));
        }

        return list;
    }

    public CustomerData buildCustomer(ResultSet rs) throws SQLException {
        rs.next();
        return new CustomerData(
                rs.getInt("id"),
                rs.getString("phone"),
                rs.getString("address")
        );
    }
}
