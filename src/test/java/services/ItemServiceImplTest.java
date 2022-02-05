package services;

import models.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.ItemRepo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {
    @Mock
    ItemRepo ir = Mockito.mock(ItemRepo.class);
    ItemServiceImpl is = new ItemServiceImpl(ir);

    Item item1 = new Item(3, "Steak", 432, true);
    Item item2 = new Item(2, "Fries", 2500, false);

    @Test
    void saveItem() {
        Mockito.when(ir.save(item1)).thenReturn(item1);
        Mockito.when(ir.save(item2)).thenReturn(item2);

        assertEquals(item1, is.saveItem(item1));
        assertEquals(item2, is.saveItem(item2));
        assertNotEquals(item1, is.saveItem(item2));
    }

    @Test
    void getItemById() {
        try {
            Mockito.when(ir.getById(3)).thenReturn(item1);
            Mockito.when(ir.getById(2)).thenReturn(item2);

            assertEquals(item2, is.getItemById(2));
            assertEquals(item1, is.getItemById(3));
            assertNotEquals(item1, is.getItemById(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getItems() {
        try {
            Mockito.when(ir.getAll()).thenReturn(new LinkedList<>(Arrays.asList(item1, item2)));

            assertEquals(2, is.getItems().size());
            assertTrue(is.getItems().stream().findFirst().isPresent());
            assertEquals(item1, is.getItems().stream().findFirst().get());
            assertNotEquals(item2, is.getItems().stream().findFirst().get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        Mockito.when(ir.delete(3)).thenReturn(true);
        Mockito.when(ir.delete(2)).thenReturn(true);
        Mockito.when(ir.delete(400)).thenReturn(false);

        assertTrue(is.delete(2));
        assertTrue(is.delete(3));
        assertFalse(is.delete(400));
    }
}