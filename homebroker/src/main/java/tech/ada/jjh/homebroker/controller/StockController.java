package tech.ada.jjh.homebroker.controller;

import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.service.create.CreateStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController{
    CreateStockService createStockService;
    FetchStockService fetchStockService;

    public StockController(CreateStockService createStockService, FetchStockService fetchStockService){
        this.createStockService = createStockService;
        this.fetchStockService = fetchStockService;

    }

    @GetMapping()
    public List<Stock> getAll(){
        return fetchStockService.fetchAll();

    }

    @GetMapping("/ticker/{ticker}")
    public Optional<Stock> findStockByTicker(@PathVariable String ticker){
        return fetchStockService.fetchByTicker(ticker);

    }

    @GetMapping("/{id}")
    public Optional<Stock> findStockById(@PathVariable Long id){
        return fetchStockService.fetchById(id);

    }

    @PostMapping()
    public Stock insertStock(@RequestBody Stock stock){
        return createStockService.createStock(stock);

    }

}
