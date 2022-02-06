package services;

import models.Customer;
import models.Order;
import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.OrderRepo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceImplTest {

    @Mock
    OrderRepo or = Mockito.mock(OrderRepo.class);
    OrderServiceImpl order = new OrderServiceImpl(or);
    Customer temp = new Customer("lady");
    Customer temp2 = new Customer("Man");

    Order order1 = new Order(4,temp,50);
    Order order2 = new Order(5,temp2,60);

    @Test
    void saveOrder(){
        Mockito.when(order.saveOrder(order1)).thenReturn(order1);
        Mockito.when(order.saveOrder(order2)).thenReturn(order2);

        assertEquals(order1, order.saveOrder(order1));
        assertEquals(order2, order.saveOrder(order2));
        assertNotEquals(order1, order.saveOrder(order2));

    }

    @Test
    void  getById() throws SQLException {
        Mockito.when(order.getOrderById(4)).thenReturn(order1);
        Mockito.when(order.getOrderById(5)).thenReturn(order2);

        assertEquals(order1,order.getOrderById(4));
        assertEquals(order2,order.getOrderById(5));
        assertNotEquals(order1,order.getOrderById(5));
    }


    @Test
    void getOrders() {
        try {
            Mockito.when(or.getAll()).thenReturn(new LinkedList<>(Arrays.asList(order1,order2)));

            assertEquals(2,order.getOrders().size());
            assertEquals(order1, order.getOrders().stream().findFirst().get());
            assertEquals(order2, order.getOrders().getLast());
            assertNotEquals(order2, order.getOrders().stream().findFirst().get());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @Test
    void deleteOrder(){
        Mockito.when(order.deleteOrder(4)).thenReturn(true);
        Mockito.when(order.deleteOrder(5)).thenReturn(true);
        Mockito.when(order.deleteOrder(50)).thenReturn(false);

        assertTrue(order.deleteOrder(4));
        assertTrue(order.deleteOrder(5));
        assertFalse(order.deleteOrder(50));
    }
}
