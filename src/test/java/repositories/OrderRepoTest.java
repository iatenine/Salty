package repositories;

import ORM.PepperORM;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepoTest {

    @Test
    void save() {
        // This should be overwritten later
        // For now it serves to test the build config in AWS CodeBuild is working as expected
        PepperORM.connect();
        HashMap<String, Class> columns = new HashMap<>();
        columns.put("name", String.class);
        columns.put("price", Integer.class);
        columns.put("pi", Double.class);
        String tablename = PepperORM.createTable("this_table", columns);
        assertEquals("this_table", tablename);
        PepperORM.dropTable("this_table");
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }
}