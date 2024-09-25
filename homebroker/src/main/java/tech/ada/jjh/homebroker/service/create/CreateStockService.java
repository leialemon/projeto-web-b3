package tech.ada.jjh.homebroker.service.create;

import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

public class CreateStockService{
    private final StockRepository stockRepository;

    public CreateStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;

    }

    public Stock insertStock(Stock stock){
        return stockRepository.save(stock);

    }

}
