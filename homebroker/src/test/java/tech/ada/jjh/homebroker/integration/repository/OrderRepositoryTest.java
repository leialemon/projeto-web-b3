package tech.ada.jjh.homebroker.integration.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    Order order1;
    Order order2;

    @BeforeEach
    public void setUp(){
        order1 = new Order();
        order1.setTotalPrice(BigDecimal.valueOf(15));
        order1.setStockQuantity(1);
        orderRepository.save(order1);

        order2 = new Order();
        order2.setTotalPrice(BigDecimal.valueOf(100));
        order2.setStockQuantity(2);
        orderRepository.save(order2);
    }

    @AfterEach
    public void tearDown(){
        orderRepository.delete(order1);
        orderRepository.delete(order2);
    }

    @Test
    void mustSaveAnOrderAndGenerateASequentialId(){
        Order orderToBeSaved = new Order();
        orderToBeSaved.setTotalPrice(BigDecimal.TWO);

        Order orderSaved = orderRepository.save(orderToBeSaved);

        Assertions.assertNotNull(orderSaved);
        Assertions.assertNotNull(orderSaved.getId());
        Assertions.assertNotNull(orderSaved.getUuid());
        Assertions.assertEquals(orderToBeSaved.getTotalPrice(), orderSaved.getTotalPrice());
    }

    @Test
    void mustFindASavedOrderById(){
        Optional<Order> returnedOrder = orderRepository.findById(order1.getId());

        Assertions.assertTrue(returnedOrder.isPresent());
        Assertions.assertEquals(order1.getTotalPrice() , returnedOrder.get().getTotalPrice());
    }

    @Test
    void mustFindASavedOrderByUuid(){
        Order returnedOrder = orderRepository.findByUuid(order1.getUuid());

        Assertions.assertNotNull(returnedOrder);
        Assertions.assertEquals(order1.getTotalPrice() , returnedOrder.getTotalPrice());
    }

    @Test
    void mustReturnAllSavedOrders(){
        List<Order> savedOrdersList = orderRepository.findAll();

        Assertions.assertNotNull(savedOrdersList);
        Assertions.assertFalse(savedOrdersList.isEmpty());
    }

    @Test
    void mustUpdateASavedOrder(){
        Optional <Order> orderToBeUpdated = orderRepository.findById(order1.getId());
        Order updatedOrder = orderToBeUpdated.orElseThrow();

        updatedOrder.setTotalPrice(BigDecimal.ZERO);
        orderRepository.save(updatedOrder);
        Optional <Order> returnedOrder = orderRepository.findById(order1.getId());

        Assertions.assertTrue(returnedOrder.isPresent());
        Assertions.assertEquals(order1.getUuid(), returnedOrder.get().getUuid());
        Assertions.assertEquals(BigDecimal.ZERO, returnedOrder.get().getTotalPrice());
    }
}