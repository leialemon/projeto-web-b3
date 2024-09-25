package tech.ada.jjh.homebroker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;

@Controller
@RequestMapping("api/v1/orders")
public class OrderController{
    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService){
        this.createOrderService = createOrderService;

    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return  createOrderService.createOrder(order);

    }

}
