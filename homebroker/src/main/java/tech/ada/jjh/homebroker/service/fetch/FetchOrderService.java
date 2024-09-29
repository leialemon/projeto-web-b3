package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.mapper.OrderMapper;
import tech.ada.jjh.homebroker.repository.OrderRepository;

import java.util.List;

@Service
public class FetchOrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public FetchOrderService(OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTOResponse> findAll(){
        return orderMapper.listToDto(orderRepository.findAll());
    }

    public OrderDTOResponse findByUuid(String uuid){
        return orderMapper.toDto(orderRepository.findByUuid(uuid));
    }
}
