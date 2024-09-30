package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.exception.ResourceNotFoundException;
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
    public List<StockDTO> getAll(){
        return fetchStockService.fetchAll();

    }

    @GetMapping("/ticker/{ticker}")
    public Optional<StockDTO> findStockByTicker(@PathVariable String ticker){
        Optional<StockDTO> stock = fetchStockService.fetchByTicker(ticker);
        if (stock.isEmpty()) {
            throw new ResourceNotFoundException("Stock com ticker " + ticker + " não encontrado.");
        }
        return stock;
    }

    @GetMapping("/{id}")
    public Optional<StockDTO> findStockById(@PathVariable Long id){
        return fetchStockService.fetchById(id);

    }

    @PostMapping()
    public StockDTO insertStock(@Valid @RequestBody StockDTO stock){
        try{
            return createStockService.execute(stock);

        }catch (Exception e){
            throw new RuntimeException("Erro ao inserir a ação: " + e.getMessage() + ". Ela possivelmente já existe na base de dados");

        }

    }

}
