package repositories;


import models.CustomerData;

import java.sql.SQLException;
import java.util.LinkedList;

public class CustomerDataRepo extends SaltyRepo<CustomerData>{

    @Override
    public CustomerData save(CustomerData customerData) {
        return null;
    }

    @Override
    public CustomerData getById(int id) throws SQLException {
        return null;
    }

    @Override
    public LinkedList<CustomerData> getAll() throws SQLException {
        return null;
    }
}
