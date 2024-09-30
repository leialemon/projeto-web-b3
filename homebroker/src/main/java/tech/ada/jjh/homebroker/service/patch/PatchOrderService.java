package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.OrderStatus;
import tech.ada.jjh.homebroker.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
public class PatchOrderService {
    private final OrderRepository orderRepository;
    private final PatchUserService patchUserService;
    private final OrderMapper orderMapper;

    public PatchOrderService(OrderRepository orderRepository, PatchUserService patchUserService, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.patchUserService = patchUserService;
        this.orderMapper = orderMapper;
    }

    public OrderDTOResponse executeOrder(OrderDTOResponse order){
        Order entity = orderRepository.findByUuid(order.getUuid());
        entity.setDateTimeExecution(LocalDateTime.now());
        if (entity.getStatus().equals(OrderStatus.PENDING)){
            if(entity.getDateTimeExecution().isAfter(entity.getDateTimeCreation().plusSeconds(30))){
                entity.setStatus(OrderStatus.EXPIRED);
            } else {
                patchUserService.modifyUserBalance(entity);
                patchUserService.modifyUserStock(entity);
                entity.setStatus(OrderStatus.EXECUTED);
            }
        }
        return orderMapper.toDto(orderRepository.save(entity));
    }

    public OrderDTOResponse cancelOrder(OrderDTOResponse order){
        Order entity = orderRepository.findByUuid(order.getUuid());
        entity.setDateTimeExecution(LocalDateTime.now());
        if (entity.getStatus().equals(OrderStatus.PENDING)){
            if(entity.getDateTimeExecution().isAfter(entity.getDateTimeCreation().plusMinutes(10))){
                entity.setStatus(OrderStatus.EXPIRED);
            } else {
                entity.setStatus(OrderStatus.CANCELED_BY_USER);
            }
        }
        return orderMapper.toDto(orderRepository.save(entity));
    }
}

