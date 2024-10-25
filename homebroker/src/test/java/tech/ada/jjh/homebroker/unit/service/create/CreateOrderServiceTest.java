package tech.ada.jjh.homebroker.unit.service.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.config.IncorrectPassword;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.*;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.service.create.CreateFeeService;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CreateOrderServiceTest {

    @Mock
    FetchUserService fetchUserService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;

    @Mock
    FetchStockService fetchStockService;

    @Mock
    CreateFeeService createFeeService;

    @InjectMocks
    CreateOrderService createOrderService;

    @Test
    void mustReturnAnOrderDTOResponseFromTheCreatedOrder() throws Exception {
        OrderDTORequest orderDTORequest = new OrderDTORequest();

        Stock stock = new Stock();
        stock.setTicker("ticker");
        stock.setPrice(BigDecimal.valueOf(2));

        AppUser user = new AppUser();
        user.setPassword("password");
        user.setCpf("1234567");
        user.setBalance(BigDecimal.valueOf(20));

        orderDTORequest.setUserCpf(user.getCpf());
        orderDTORequest.setUserPassword(user.getPassword());
        orderDTORequest.setType(OrderType.BUYING);
        orderDTORequest.setStockTicker(stock.getTicker());
        orderDTORequest.setStockQuantity(2);

        Order order = new Order();
        order.setType(orderDTORequest.getType());
        order.setStockQuantity(orderDTORequest.getStockQuantity());

        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();

        Mockito.when(fetchStockService.getByTicker(stock.getTicker())).thenReturn(stock);
        Mockito.when(fetchUserService.getByCpf(user.getCpf())).thenReturn(user);
        Mockito.when(orderMapper.toEntity(orderDTORequest)).thenReturn(order);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTOResponse);

        OrderDTOResponse returned = createOrderService.create(orderDTORequest);

        Assertions.assertNotNull(returned);
    }

    @Test
    void mustThrowAnExceptionWhenTheWrongPasswordIsProvided() {
        OrderDTORequest orderDTORequest = new OrderDTORequest();
        AppUser user = new AppUser();
        user.setPassword("password");
        user.setCpf("1234567");
        user.setBalance(BigDecimal.valueOf(20));
        orderDTORequest.setUserCpf(user.getCpf());
        orderDTORequest.setUserPassword("wrong password");
        Mockito.when(fetchUserService.getByCpf(user.getCpf())).thenReturn(user);

        RuntimeException returned = Assertions.assertThrows(IncorrectPassword.class, () -> createOrderService.create(orderDTORequest));
        Assertions.assertNotNull(returned);
        Assertions.assertEquals("Senha de usuário incorreta", returned.getMessage());
    }
}