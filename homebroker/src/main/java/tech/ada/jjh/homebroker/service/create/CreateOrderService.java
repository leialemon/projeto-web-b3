package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.FeeType;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateOrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public CreateOrderService(OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO execute(OrderDTO order){
        var entity = orderMapper.toEntity(order);
        List<Fee> fees = new ArrayList<>();
        Fee b3emolument = new Fee();
        b3emolument.setAmount(0.03);
        b3emolument.setName("b3emoluments");
        b3emolument.setType(FeeType.EMOLUMENT);
        fees.add(b3emolument);
        Fee taxes = new Fee();
        taxes.setName("taxes");
        taxes.setAmount(0.02);
        taxes.setType(FeeType.TAX);
        fees.add(taxes);
        entity.setFees(fees);
        entity = orderRepository.save(entity);
        return orderMapper.toDto(entity);
    }

}
