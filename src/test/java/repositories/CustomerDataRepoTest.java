package repositories;

import ORM.PepperORM;
import models.CustomerData;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataRepoTest {
    String tableName = "customer_data";
    int id1 = 0;
    int id2 = 0;
    CustomerData sampleData1 = new CustomerData("12345", "123 Somewhere Place");
    CustomerData sampleData2 = new CustomerData("987654321", "321 Anywhere Ln");
    CustomerData ret1 = null;
    CustomerData ret2 = null;
    CustomerDataRepo cdr = new CustomerDataRepo();

    @BeforeAll
    static void setup(){
        assertTrue(PepperORM.connect());
    }

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
    }

    @Test
    void getById() {
        try{
        CustomerData test1 = cdr.getById(ret1.getId());
        CustomerData test2 = cdr.getById(ret2.getId());

        assertEquals(test1.getId(), ret1.getId());
        assertNotEquals(test2.getId(), ret1.getId());
        } catch (SQLException e){
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {
    }

    @Test
    void buildCustomer() {
    }
}