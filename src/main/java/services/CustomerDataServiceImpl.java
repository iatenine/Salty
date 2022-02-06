package services;

import models.CustomerData;
import repositories.CustomerDataRepo;

import java.sql.SQLException;
import java.util.List;

public class CustomerDataServiceImpl implements CustomerDataService {
    final private CustomerDataRepo repo;

    public CustomerDataServiceImpl(CustomerDataRepo repo){
        this.repo = repo;
    }

    @Override
    public CustomerData saveCustomerData(CustomerData cd) throws SQLException {
        return repo.save(cd);
    }

    @Override
    public CustomerData getCustomerDataById(int id) throws SQLException {
        return repo.getById(id);
    }

    @Override
    public List<CustomerData> getCustomerDate() throws SQLException {
        return repo.getAll();
    }

    @Override
    public boolean deleteCustomerData(int id) {
        return repo.delete(id, "customer_data");
    }
}
