package services;

import models.CustomerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.CustomerDataRepo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataServiceImplTest {
    @Mock
    CustomerDataRepo cdr = Mockito.mock(CustomerDataRepo.class);
    CustomerDataServiceImpl cds = new CustomerDataServiceImpl(cdr);
    int mockId1 = 4;
    int mockId2 = 12;
    int fakeId = 423942;
    String[] numbers = {"842134832", "8675309"};
    String[] addresses = {
            "321 Elm St",
            "483 Nightmare Ln"
    };
    CustomerData[] sampleData = {
            new CustomerData(mockId1, numbers[0], addresses[0]),
            new CustomerData(mockId2, numbers[1], addresses[1])
    };

    @Test
    void saveCustomerData() {
        try{
        Mockito.when(cdr.save(sampleData[0])).thenReturn(
                sampleData[0]
        );
        Mockito.when(cdr.save(sampleData[1])).thenReturn(
                sampleData[1]
        );

        assertEquals(sampleData[0], cds.saveCustomerData(sampleData[0]));
        assertEquals(sampleData[1], cds.saveCustomerData(sampleData[1]));
        assertNotEquals(fakeId, cds.saveCustomerData(sampleData[0]).getId());
        } catch (SQLException e){
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void getCustomerDataById() {
        try {
            Mockito.when(cdr.getById(mockId1)).thenReturn(sampleData[0]);
            Mockito.when(cdr.getById(mockId2)).thenReturn(sampleData[1]);

            assertEquals(addresses[0], cds.getCustomerDataById(mockId1).getAddress());
            assertEquals(numbers[1], cds.getCustomerDataById(mockId2).getPhone());
            assertNotEquals(numbers[1], cds.getCustomerDataById(mockId1).getPhone());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCustomerDate() {
        try {
            Mockito.when(cdr.getAll()).thenReturn(new LinkedList<>(Arrays.asList(sampleData)));

            assertTrue(cds.getCustomerDate().stream().findFirst().isPresent());
            assertEquals(sampleData[0], cds.getCustomerDate().stream().findFirst().get());
            assertEquals(2, cds.getCustomerDate().size());
            assertNotEquals(sampleData[1], cds.getCustomerDate().stream().findFirst().get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteCustomerData() {
        Mockito.when(cdr.delete(mockId1)).thenReturn(true);
        Mockito.when(cdr.delete(mockId2)).thenReturn(true);
        Mockito.when(cdr.delete(fakeId)).thenReturn(false);

        assertFalse(cds.deleteCustomerData(fakeId));
        assertTrue(cds.deleteCustomerData(mockId1));
        assertTrue(cds.deleteCustomerData(mockId2));
    }
}