package repositories;

import ORM.PepperORM;
import models.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepoTest {
    String tableName = "items";
    int id1 = 0;
    int id2 = 0;
    Item sampleData1 = new Item("French Fries", 2500, false);
    Item sampleData2 = new Item("British Fries", 300, true);
    Item ret1 = null;
    Item ret2 = null;
    ItemRepo repo = new ItemRepo();

    @BeforeAll
    static void setup(){
        assertTrue(PepperORM.connect());
    }

    @BeforeEach
    void addRows(){
        ret1 = new Item(
                PepperORM.addRow(tableName,
                        sampleData1.getName(),
                        sampleData1.getPrice(),
                        sampleData1.isAvailable()
        ), sampleData1.getName(),
                sampleData1.getPrice(),
                sampleData1.isAvailable());
        ret2 = new Item(
                PepperORM.addRow(tableName,
                        sampleData2.getName(),
                        sampleData2.getPrice(),
                        sampleData2.isAvailable()
        ),
                sampleData2.getName(),
                sampleData2.getPrice(),
                sampleData2.isAvailable());

        assertNotEquals(0, ret1.getId());
        assertNotEquals(0, ret2.getId());

        assertEquals(sampleData2.getName(), ret2.getName());
        assertEquals(sampleData1.getPrice(), ret1.getPrice());
    }

    @AfterEach
    void dropRows(){
        PepperORM.deleteRow(tableName, ret1.getId());
        PepperORM.deleteRow(tableName, ret2.getId());
    }

    @Test
    void save() {
        try {
            Item test = repo.save(sampleData1);
            assertNotNull(test);
            id1 = test.getId();

            assertNotEquals(test.getId(), sampleData1.getId());
            assertEquals(test.getPrice(), sampleData1.getPrice());

            Item test2 = repo.save(
                    new Item(
                            test.getId(),
                            "",
                            40,
                            true
                    )
            );
            assertEquals(test.getId(), test2.getId());
            assertEquals(40, test2.getPrice());
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
            Item test1 = repo.getById(ret1.getId());
            Item test2 = repo.getById(ret2.getId());

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
            Item test = repo.save(sampleData1);
            assertNotNull(test);
            id1 = test.getId();
            LinkedList<Item> list = repo.getAll();

            assertEquals(id1, list.getLast().getId());
            assertNotEquals(id1, list.getFirst().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PepperORM.deleteRow(tableName, id1);
        }


    }

    @Test
    void buildItem() {
        try {
            String[] allcols = {"*"};
            ResultSet rs = PepperORM.getRow(tableName, ret1.getId(), allcols);

            Item test = repo.buildItem(rs);
            assertEquals(ret1.getPrice(), test.getPrice());
            assertNotEquals(ret2.getId(), test.getId());
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }
    }
}