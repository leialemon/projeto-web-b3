package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FetchStockService {
    private final StockRepository stockRepository;

    public FetchStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;

    }

    public Optional<Stock> fetchById(Long id){
        return stockRepository.findById(id);

    }

    public Optional<Stock> fetchByTicker(String ticker){
        return stockRepository.findByTicker(ticker);

    }

    public List<Stock> fetchAll(){
        return stockRepository.findAll();

    }

}
