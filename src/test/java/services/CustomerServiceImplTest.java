package services;

import models.Customer;
import models.CustomerData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.CustomerDataRepo;
import repositories.CustomerRepo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    @Mock
    CustomerRepo cr = Mockito.mock(CustomerRepo.class);
    @Mock
    CustomerDataRepo cdr = Mockito.mock(CustomerDataRepo.class);
    CustomerServiceImpl custService = new CustomerServiceImpl(cr, cdr);
    int mockId = 3;
    Customer customer = new Customer("Ladybird");


    @Test
    void saveCustomer() {
        Mockito.when(
                cr.save(customer)
        ).thenReturn(
                new Customer(mockId, "Ladybird", null)
        );

        assertEquals(mockId, custService.saveCustomer(customer).getId());
        assertEquals("Ladybird", custService.saveCustomer(customer).getCustomer_name());
        assertNull(custService.saveCustomer(customer).getCustomer_data());
    }

    @Test
    void getCustomerById() {
        try {
            Mockito.when(cr.getById(mockId)).thenReturn(
                new Customer(mockId, "Ladybird", new CustomerData(mockId, null, null)));
            Mockito.when(cdr.getById(mockId)).thenReturn(
                    new CustomerData(mockId, "3218675309", "123 Anywhere St"));

            assertEquals(mockId, custService.getCustomerById(mockId).getId());
            assertEquals("Ladybird", custService.getCustomerById(mockId).getCustomer_name());
            assertNotNull(custService.getCustomerById(mockId).getCustomer_data().getPhone());
            assertNotNull(custService.getCustomerById(mockId).getCustomer_data().getAddress());
            assertEquals(mockId, custService.getCustomerById(mockId).getCustomer_data().getId());
            assertEquals("123 Anywhere St", custService.getCustomerById(mockId).getCustomer_data().getAddress());
            assertNotEquals("Wrong address", custService.getCustomerById(mockId).getCustomer_data().getAddress());
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void getCustomers() {
        Customer c1 = new Customer(mockId, "Ladybird", new CustomerData(3, "", ""));
        Customer c2 = new Customer(mockId + 1, "Bobby", new CustomerData(4, "", ""));
        try {
            Mockito.when(cr.getAll()).thenReturn(new LinkedList<>(Arrays.asList(c1, c2)));

            assertEquals(mockId, custService.getCustomers().getFirst().getId());
            assertEquals(mockId+1, custService.getCustomers().getLast().getId());
            assertNotEquals(mockId - 1, custService.getCustomers().getLast().getId());

            assertEquals("Ladybird", custService.getCustomers().getFirst().getCustomer_name());
            assertEquals("Bobby", custService.getCustomers().getLast().getCustomer_name());
            assertNotEquals("Hank", custService.getCustomers().getLast().getCustomer_name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteCustomer() {
        String tableName = "cusomters";
        Mockito.when(cr.delete(mockId, tableName)).thenReturn(true);
        Mockito.when(cr.delete(383982839, tableName)).thenReturn(false);

        assertTrue(custService.deleteCustomer(mockId));
        assertFalse(custService.deleteCustomer(383982839));
    }
}