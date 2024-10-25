package tech.ada.jjh.homebroker.unit.service.fetch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FetchOrderServiceTest {

    @InjectMocks
    FetchOrderService fetchOrderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;

    @Test
    void mustReturnAListOfOrders() {
        Order order1 = new Order();
        order1.setUuid("12345");
        OrderDTOResponse orderDTOResponse1 = new OrderDTOResponse();
        orderDTOResponse1.setUuid(order1.getUuid());

        Order order2 = new Order();
        order2.setUuid("678910");
        OrderDTOResponse orderDTOResponse2 = new OrderDTOResponse();
        orderDTOResponse2.setUuid(order2.getUuid());

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        List<OrderDTOResponse> orderDTOResponses = new ArrayList<>();
        orderDTOResponses.add(orderDTOResponse1);
        orderDTOResponses.add(orderDTOResponse2);

        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        Mockito.when(orderMapper.listToDto(orders)).thenReturn(orderDTOResponses);

        List <OrderDTOResponse> returned = fetchOrderService.findAll();

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(returned, orderDTOResponses);
    }

    @Test
    void mustReturnAnEmptyList(){
        List<Order> orders = new ArrayList<>();
        List<OrderDTOResponse> orderDTOResponses = new ArrayList<>();

        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        Mockito.when(orderMapper.listToDto(orders)).thenReturn(orderDTOResponses);

        List <OrderDTOResponse> returned = fetchOrderService.findAll();

        Assertions.assertNotNull(returned);
        Assertions.assertTrue(returned.isEmpty());
    }

    @Test
    void mustReturnTheCorrectOrder() {
        Order order1 = new Order();
        order1.setUuid("12345");
        OrderDTOResponse orderDTOResponse1 = new OrderDTOResponse();
        orderDTOResponse1.setUuid(order1.getUuid());

        Mockito.when(orderRepository.findByUuid(order1.getUuid())).thenReturn(order1);
        Mockito.when(orderMapper.toDto(order1)).thenReturn(orderDTOResponse1);

        OrderDTOResponse returned = fetchOrderService.findByUuid("12345");

        Assertions.assertNotNull(returned);
        Assertions.assertEquals("12345", returned.getUuid());
    }
}