package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.jjh.homebroker.service.create.CreateStockService;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController{
    CreateStockService createStockService;

    public StockController(CreateStockService createStockService){
        this.createStockService = createStockService;

    }

}
