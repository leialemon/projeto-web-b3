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
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final StockRepository stockRepository;
    private final CreateFeeService createFeeService;


    public CreateOrderService(OrderRepository orderRepository, OrderMapper orderMapper, StockRepository stockRepository, CreateFeeService createFeeService, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.stockRepository = stockRepository;
        this.createFeeService = createFeeService;
        this.userRepository = userRepository;
    }

    public OrderDTOResponse execute(OrderDTORequest order){
        var entity = orderMapper.toEntity(order);
        entity.setStock(setStock(order.getStockTicker()));
        entity.setFees(createFeeService.createBasicFees());
        entity.setUser(setUser(order.getUserCpf()));
        calculateTotalPrice(entity);
        calculateRawPrice(entity);
        checkBalance(entity);
        entity.setDateTimeCreation(LocalDateTime.now());
        entity = orderRepository.save(entity);
        return orderMapper.toDto(entity);
    }

    public void calculateRawPrice(Order order){
        BigDecimal rawTotal = order.getStock().getPrice().multiply(BigDecimal.valueOf(order.getStockQuantity()));
        order.setRawPrice(rawTotal);
    }

    public void calculateTotalPrice(Order order){
        List<Fee> fees = new ArrayList<>(order.getPortfolio().getBroker().getFees());
        BigDecimal rawTotal = order.getRawPrice();
        BigDecimal feeTotal = BigDecimal.ZERO;
        for (Fee fee : fees){
            feeTotal = feeTotal.add(fee.getType().getCalculationRule().calculate(rawTotal, fee.getAmount()));
        }
        rawTotal = rawTotal.add(feeTotal);
        order.setTotalPrice(rawTotal);
    }

    public AppUser setUser(String cpf){
        Optional<AppUser> user = userRepository.findByCpf(cpf);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    public Stock setStock(String ticker){
        Optional<Stock> stock = stockRepository.findByTicker(ticker);
        if (stock.isPresent()){ return stock.get();}
        return null;
    }

    public void checkBalance(Order order){
        if (order.getTotalPrice().compareTo(order.getUser().getBalance()) > 0){
            order.setStatus(OrderStatus.CANCELED_LACK_FUNDS);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
    }
}
