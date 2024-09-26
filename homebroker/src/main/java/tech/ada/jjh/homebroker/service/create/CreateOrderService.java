package tech.ada.jjh.homebroker.service.create;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.mapper.AppUserModelMapper;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.mapper.OrderModelMapper;
import tech.ada.jjh.homebroker.repository.OrderRepository;

@Service
public class CreateOrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public CreateOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        this.orderMapper = Mappers.getMapper(OrderMapper.class);


    }

    public OrderDTO execute(OrderDTO order){
        var entity = OrderModelMapper.toEntity(order);
        entity = orderRepository.save(entity);
        return OrderModelMapper.toDto(entity);

    }

}
