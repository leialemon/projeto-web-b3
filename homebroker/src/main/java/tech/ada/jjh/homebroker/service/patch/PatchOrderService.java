package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.repository.OrderRepository;

@Service
public class PatchOrderService {
    private final OrderRepository orderRepository;

    public PatchOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
//Executar ou cancelar ordem. → checar se ela expirou
// Se der tempo: checar se o preço da ação mudou