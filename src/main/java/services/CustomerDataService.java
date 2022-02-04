package services;

import models.CustomerData;

import java.util.List;

public interface CustomerDataService {
    CustomerData saveCustomerData(CustomerData cd);
    CustomerData getCustomerDataById(int id);
    List<CustomerData> getCustomerDate();
    boolean deleteCustomerData(int id);
}
