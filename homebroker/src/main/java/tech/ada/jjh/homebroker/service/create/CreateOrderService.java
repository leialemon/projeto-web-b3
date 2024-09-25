package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.repository.OrderRepository;

@Service
public class CreateOrderService{
    private final OrderRepository orderRepository;

    public CreateOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;

    }

    public Order createOrder(Order order){
        return orderRepository.save(order);

    }

}
