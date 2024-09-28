package tech.ada.jjh.homebroker.service.create;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.*;
import tech.ada.jjh.homebroker.repository.FeeRepository;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.repository.StockRepository;
import tech.ada.jjh.homebroker.repository.UserRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateOrderService{
    private final FetchUserService fetchUserService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final FetchStockService fetchStockService;
    private final CreateFeeService createFeeService;


    public CreateOrderService(OrderRepository orderRepository, OrderMapper orderMapper, FetchStockService fetchStockService, CreateFeeService createFeeService, FetchUserService fetchUserService){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.fetchStockService = fetchStockService;
        this.createFeeService = createFeeService;
        this.fetchUserService = fetchUserService;
    }

    public OrderDTOResponse execute(OrderDTORequest order){
        var entity = orderMapper.toEntity(order);
        entity.setStock(setStock(order.getStockTicker()));
        entity.setFees(createFeeService.createBasicFees());
        entity.setUser(setUser(order.getUserCpf()));
        calculateRawPrice(entity);
        calculateTotalPrice(entity);
        checkBalance(entity);
        entity.setDateTimeCreation(LocalDateTime.now());
        entity = orderRepository.save(entity);
        return orderMapper.toDto(entity);
    }

    private void calculateRawPrice(Order order){
        BigDecimal rawTotal = order.getStock().getPrice().multiply(BigDecimal.valueOf(order.getStockQuantity()));
        order.setRawPrice(rawTotal);
    }

    private void calculateTotalPrice(Order order){
        BigDecimal rawTotal = order.getRawPrice();
        BigDecimal feeTotal = BigDecimal.ZERO;
        for (Fee fee : order.getFees()){
            feeTotal = feeTotal.add(fee.getType().getCalculationRule().calculate(rawTotal, fee.getAmount()));
        }
        rawTotal = rawTotal.add(feeTotal);
        order.setTotalPrice(rawTotal);
    }

    private AppUser setUser(String cpf){
        return fetchUserService.getByCpf(cpf);
    }

    private Stock setStock(String ticker){
        return fetchStockService.getByTicker(ticker).orElse(null);
    }

    private void checkBalance(Order order){
        if (order.getTotalPrice().compareTo(order.getUser().getBalance()) > 0){
            order.setStatus(OrderStatus.CANCELED_LACK_FUNDS);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
    }
}
