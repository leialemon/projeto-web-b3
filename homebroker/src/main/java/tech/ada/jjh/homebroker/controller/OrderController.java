package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;
import tech.ada.jjh.homebroker.service.patch.PatchOrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController{
    private final CreateOrderService createOrderService;
    private final FetchOrderService fetchOrderService;
    private final PatchOrderService patchOrderService;

    public OrderController(CreateOrderService createOrderService, FetchOrderService fetchOrderService, PatchOrderService patchOrderService) {
        this.createOrderService = createOrderService;
        this.fetchOrderService = fetchOrderService;
        this.patchOrderService = patchOrderService;
    }

    @GetMapping()
    public List<OrderDTOResponse> fetchAll(){
        return fetchOrderService.findAll();
    }

    @PostMapping()
    public OrderDTOResponse create(@Valid @RequestBody OrderDTORequest order){
        return  createOrderService.create(order);
    }

    @PatchMapping("confirm/{uuid}")
    public OrderDTOResponse execute(@PathVariable String uuid){
        return patchOrderService.executeOrder(fetchOrderService.findByUuid(uuid));
    }

    @PatchMapping("cancel/{uuid}")
    public OrderDTOResponse cancel(@PathVariable String uuid){
        return patchOrderService.cancelOrder(fetchOrderService.findByUuid(uuid));
    }

}
