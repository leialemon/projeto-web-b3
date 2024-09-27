package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController{
    private final CreateOrderService createOrderService;
    private final FetchOrderService fetchOrderService;

    public OrderController(CreateOrderService createOrderService, FetchOrderService fetchOrderService) {
        this.createOrderService = createOrderService;
        this.fetchOrderService = fetchOrderService;
    }

    @GetMapping()
    public List<OrderDTOResponse> fetchAll(){
        return fetchOrderService.findAll();
    }

    @PostMapping()
    public OrderDTOResponse execute(@Valid @RequestBody OrderDTORequest order){
        return  createOrderService.execute(order);
    }

}
