package services;

import models.CustomerData;
import repositories.CustomerDataRepo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDataService {
    CustomerData saveCustomerData(CustomerData cd) throws SQLException;
    CustomerData getCustomerDataById(int id) throws SQLException;
    List<CustomerData> getCustomerDate() throws SQLException;
    boolean deleteCustomerData(int id);
}
