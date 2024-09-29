package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.OrderStatus;
import tech.ada.jjh.homebroker.repository.OrderRepository;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;

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
        Order entity = orderMapper.fromResponseToEntity(order);
        entity.setDateTimeExecution(LocalDateTime.now());
        if (entity.getDateTimeExecution().isAfter(entity.getDateTimeCreation().plusMinutes(15))){
            entity.setStatus(OrderStatus.EXPIRED);
        } else {
            entity.setStatus(OrderStatus.EXECUTED);
            patchUserService.modifyUserBalance(entity);
        }
        return orderMapper.toDto(entity);
    }

    public OrderDTOResponse cancelOrder(OrderDTOResponse order){
        order.setStatus(OrderStatus.CANCELED_BY_USER);
        return order;
    }
}

