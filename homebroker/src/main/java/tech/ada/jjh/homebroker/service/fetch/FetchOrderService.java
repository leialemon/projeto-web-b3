package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.repository.OrderRepository;

@Service
public class FetchOrderService{
    private final OrderRepository orderRepository;

    public FetchOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;

    }

}
