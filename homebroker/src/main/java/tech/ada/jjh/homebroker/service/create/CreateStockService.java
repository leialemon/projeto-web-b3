package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

@Service
public class CreateStockService{
    private final StockRepository stockRepository;

    public CreateStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;

    }

    public Stock createStock(Stock stock){
        return stockRepository.save(stock);

    }

}
