package services;

import models.CustomerData;
import repositories.CustomerDataRepo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDataService {
    CustomerData saveCustomerData(CustomerData cd);
    CustomerData getCustomerDataById(int id) throws SQLException;
    List<CustomerData> getCustomerDate() throws SQLException;
    boolean deleteCustomerData(int id);
}
