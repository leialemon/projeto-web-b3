package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;

@Controller
@RequestMapping("api/v1/orders")
public class OrderController{
    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService){
        this.createOrderService = createOrderService;

    }

    @PostMapping
    public OrderDTO execute(@Valid @RequestBody OrderDTO order){
        return  createOrderService.execute(order);

    }

}
