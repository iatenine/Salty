package repositories;

import ORM.PepperORM;
import models.CustomerData;
import models.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataRepoTest {
    String tableName = "customer_data";
    int id1 = 0;
    int id2 = 0;
    CustomerData sampleData1 = new CustomerData("12345", "123 Somewhere Place");
    CustomerData sampleData2 = new CustomerData("987654321", "321 Anywhere Ln");
    CustomerData ret1 = null;
    CustomerData ret2 = null;
    CustomerDataRepo repo = new CustomerDataRepo();

    @BeforeEach
    void addRows(){
        ret1 = new CustomerData(
                PepperORM.addRow(tableName,
                        sampleData1.getPhone(),
                        sampleData1.getAddress()),
                sampleData1.getPhone(),
                sampleData1.getAddress()
                );
        ret2 = new CustomerData(
                PepperORM.addRow(tableName,
                        sampleData2.getPhone(),
                        sampleData2.getAddress()),
                sampleData2.getPhone(),
                sampleData2.getAddress()
        );

        assertNotEquals(0, ret1.getId());
        assertNotEquals(0, ret2.getId());

        assertEquals(sampleData2.getPhone(), ret2.getPhone());
        assertEquals(sampleData1.getAddress(), ret1.getAddress());
    }

    @AfterEach
    void dropRows(){
        PepperORM.deleteRow(tableName, ret1.getId());
        PepperORM.deleteRow(tableName, ret2.getId());
    }

    @Test
    void save() {
        try {
            CustomerData test = repo.save(sampleData1);
            assertNotNull(test);
            id1 = test.getId();

            assertNotEquals(test.getId(), sampleData1.getId());
            assertEquals(test.getAddress(), sampleData1.getAddress());

            CustomerData test2 = repo.save(
                    new CustomerData(
                            test.getId(),
                            "8675309",
                            "123 Nowhereville"
                    )
            );
            assertEquals(test.getId(), test2.getId());
            assertEquals("123 Nowhereville", test2.getAddress());
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        } finally {
            PepperORM.deleteRow(tableName, id1);
        }

    }

    @Test
    void getById() {
        try{
        CustomerData test1 = repo.getById(ret1.getId());
        CustomerData test2 = repo.getById(ret2.getId());

        assertEquals(test1.getId(), ret1.getId());
        assertNotEquals(test2.getId(), ret1.getId());
        } catch (SQLException e){
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {
        try {
            CustomerData test = repo.save(sampleData1);
            assertNotNull(test);
            id1 = test.getId();
            LinkedList<CustomerData> list = repo.getAll();

            assertEquals(id1, list.getLast().getId());
            assertNotEquals(id1, list.getFirst().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PepperORM.deleteRow(tableName, id1);
        }
    }

    @Test
    void buildCustomer() {
        try {
            String[] allcols = {"*"};
            ResultSet rs = PepperORM.getRow(tableName, ret1.getId(), allcols);
            CustomerData test = repo.buildCustomer(rs);
            assertEquals(ret1.getAddress(), test.getAddress());
            assertNotEquals(ret2.getId(), test.getId());
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }
    }
}