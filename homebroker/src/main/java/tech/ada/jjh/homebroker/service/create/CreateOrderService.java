package tech.ada.jjh.homebroker.service.create;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.FeeType;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.FeeRepository;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.repository.StockRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateOrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final StockRepository stockRepository;
    private final CreateFeeService createFeeService;


    public CreateOrderService(OrderRepository orderRepository, OrderMapper orderMapper, StockRepository stockRepository, CreateFeeService createFeeService){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.stockRepository = stockRepository;
        this.createFeeService = createFeeService;
    }

    public OrderDTOResponse execute(OrderDTORequest order){
        String ticker = order.getStockTicker();
        Optional<Stock> stock = stockRepository.findByTicker(ticker);
        var entity = orderMapper.toEntity(order);
        if (stock.isPresent()){ entity.setStock(stock.get());}
        entity.setFees(createFeeService.createBasicFees());
        calculateTotalPrice(entity);
        calculateRawPrice(entity);
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

    //Executar ou cancelar ordem. → checar se ela expirou
    //Checar se o valor da ordem é inferior ou igual ao saldo do usuário
}
