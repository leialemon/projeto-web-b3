package tech.ada.jjh.homebroker.unit.service.patch;

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
import tech.ada.jjh.homebroker.model.OrderStatus;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.service.patch.PatchOrderService;
import tech.ada.jjh.homebroker.service.patch.PatchUserService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PatchOrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;

    @Mock
    PatchUserService patchUserService;

    @InjectMocks
    PatchOrderService patchOrderService;

    @Test
    void mustConfirmAnOrder(){
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
        orderDTOResponse.setUuid("123");

        Order order = new Order();
        order.setUuid(orderDTOResponse.getUuid());
        order.setStatus(OrderStatus.PENDING);
        order.setDateTimeCreation(LocalDateTime.now());

        Mockito.when(orderRepository.findByUuid(orderDTOResponse.getUuid())).thenReturn(order);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTOResponse);

        OrderDTOResponse retorno = patchOrderService.executeOrder(orderDTOResponse);
        retorno.setStatus(order.getStatus());

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(OrderStatus.EXECUTED, retorno.getStatus());
    }

    @Test
    void mustExpireAnOrder(){
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
        orderDTOResponse.setUuid("123");

        Order order = new Order();
        order.setUuid(orderDTOResponse.getUuid());
        order.setStatus(OrderStatus.PENDING);
        order.setDateTimeCreation(LocalDateTime.of(2024, 1, 1, 22, 30));

        Mockito.when(orderRepository.findByUuid(orderDTOResponse.getUuid())).thenReturn(order);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTOResponse);

        OrderDTOResponse retorno = patchOrderService.executeOrder(orderDTOResponse);
        retorno.setStatus(order.getStatus());

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(OrderStatus.EXPIRED, retorno.getStatus());
    }

    @Test
    void mustCancelAnOrder(){
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
        orderDTOResponse.setUuid("123");

        Order order = new Order();
        order.setUuid(orderDTOResponse.getUuid());
        order.setStatus(OrderStatus.PENDING);
        order.setDateTimeCreation(LocalDateTime.now());

        Mockito.when(orderRepository.findByUuid(orderDTOResponse.getUuid())).thenReturn(order);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTOResponse);

        OrderDTOResponse retorno = patchOrderService.cancelOrder(orderDTOResponse);
        retorno.setStatus(order.getStatus());

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(OrderStatus.CANCELED_BY_USER, retorno.getStatus());
    }
}