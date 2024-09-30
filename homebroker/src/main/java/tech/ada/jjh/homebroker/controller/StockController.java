package tech.ada.jjh.homebroker.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.service.create.CreateStockService;
import tech.ada.jjh.homebroker.service.delete.DeleteStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;
import tech.ada.jjh.homebroker.service.patch.PatchStockService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stocks")
@CrossOrigin(origins = "*")
public class StockController{
    private final CreateStockService createStockService;
    private final FetchStockService fetchStockService;
    private final DeleteStockService deleteStockService;
    private final PatchStockService patchStockService;

    public StockController(CreateStockService createStockService, FetchStockService fetchStockService, DeleteStockService deleteStockService, PatchStockService patchStockService){
        this.createStockService = createStockService;
        this.fetchStockService = fetchStockService;
        this.deleteStockService = deleteStockService;
        this.patchStockService = patchStockService;
    }

    @GetMapping()
    public List<StockDTO> getAll(){
        return fetchStockService.fetchAll();

    }

    @GetMapping("/ticker/{ticker}")
    public Optional<StockDTO> findStockByTicker(@PathVariable String ticker){
        return fetchStockService.fetchByTicker(ticker.toLowerCase());

    }

    @GetMapping("/{id}")
    public Optional<StockDTO> findStockById(@PathVariable Long id){
        return fetchStockService.fetchById(id);

    }

    @PostMapping()
    public StockDTO insertStock(@Valid @RequestBody StockDTO stock){
        return createStockService.execute(stock);
    }

    @DeleteMapping("/delete/{ticker}")
    public void deleteStock(@PathVariable String ticker){
        deleteStockService.deleteStockByTicker(ticker.toLowerCase());
    }

    @PatchMapping("/alter/{ticker}/price")
    public StockDTO alterStockPrice(@PathVariable String ticker, @RequestParam("price") BigDecimal newPrice){
        return patchStockService.alterStockPrice(ticker.toLowerCase(), newPrice);
    }
}
