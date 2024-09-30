package tech.ada.jjh.homebroker.service.delete;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.config.EntityNotFoundException;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

@Service
public class DeleteStockService {

    private final StockRepository stockRepository;

    public DeleteStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    private void deleteStock(Stock stock){
        stockRepository.delete(stock);
    }

    public void deleteStockByTicker(String ticker){
        deleteStock(stockRepository.findByTicker(ticker).orElseThrow(EntityNotFoundException::new));
    }
}
