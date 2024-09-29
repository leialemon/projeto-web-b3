package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.*;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public OrderDTOResponse create(OrderDTORequest order) throws Exception {
        if (!checkPassword(order)){
            throw new Exception("Incorrect Password!");
        }
        var entity = orderMapper.toEntity(order);
        entity.setStock(setStock(order.getStockTicker()));
        entity.setFees(createFeeService.createBasicFees());
        entity.setUser(setUser(order.getUserCpf()));
        calculateRawPrice(entity);
        calculateTotalPrice(entity);
        checkBalance(entity);
        checkStocks(entity);
        entity.setDateTimeCreation(LocalDateTime.now());
        entity = orderRepository.save(entity);
        OrderDTOResponse response = orderMapper.toDto(entity);
        response.setStockTicker(entity.getStock().getTicker());
        return response;
    }

    private void calculateRawPrice(Order order){
        BigDecimal rawTotal = order.getStock().getPrice().multiply(BigDecimal.valueOf(order.getStockQuantity()));
        order.setRawPrice(rawTotal);
    }

    private void calculateTotalPrice(Order order){
        BigDecimal totalPrice = order.getRawPrice();
        BigDecimal feeTotal = BigDecimal.ZERO;
        for (Fee fee : order.getFees()){
            feeTotal = feeTotal.add(fee.getType().getCalculationRule().calculate(totalPrice, fee.getAmount()));
        }
        if (order.getType().equals(OrderType.BUYING)){
            totalPrice = totalPrice.add(feeTotal);
        } else if (order.getType().equals(OrderType.SELLING)){
            totalPrice = totalPrice.subtract(feeTotal);
        }
        order.setTotalPrice(totalPrice);
    }
    // refatorar linhas 55-59 para estarem contidas no orderType?

    private AppUser setUser(String cpf){
        return fetchUserService.getByCpf(cpf);
    }

    private Stock setStock(String ticker){
        return fetchStockService.getByTicker(ticker);
    }

    private void checkBalance(Order order){
        if (order.getTotalPrice().compareTo(order.getUser().getBalance()) > 0){
            order.setStatus(OrderStatus.CANCELED_LACK_FUNDS);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
    }

    private void checkStocks(Order order){
        if (order.getType().equals(OrderType.SELLING)){
            if (order.getUser().getPortfolio().get(order.getStock()) < order.getStockQuantity()){
                order.setStatus(OrderStatus.CANCELED_NOT_ENOUGH_STOCK);
            }
        }
    }

    private boolean checkPassword(OrderDTORequest order){
        boolean correctPassword = false;
        if(fetchUserService.getByCpf(order.getUserCpf()).getPassword().equals(order.getUserPassword())){
            correctPassword = true;
        }
        return correctPassword;
    }
}
