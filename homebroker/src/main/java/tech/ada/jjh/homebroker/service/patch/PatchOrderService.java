package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.repository.OrderRepository;

@Service
public class PatchOrderService {
    private final OrderRepository orderRepository;

    public PatchOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

//    public OrderDTOResponse executeOrder(OrderDTORequest order){
//      chamar o patchUser para modificar o saldo do usuário
//    }
}
//Executar ou cancelar ordem. → checar se ela expirou
// Apenas orders com status PENDING podem ser executadas
// Se der tempo: checar se o preço da ação mudou