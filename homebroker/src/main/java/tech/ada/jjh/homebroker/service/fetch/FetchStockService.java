package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.StockDTO;
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

    public Optional<StockDTO> fetchById(Long id){
        return stockRepository.findById(id).map(StockDTO::fromEntity);

    }

    public Optional<StockDTO> fetchByTicker(String ticker){
        return stockRepository.findByTicker(ticker).map(StockDTO::fromEntity);

    }

    public List<StockDTO> fetchAll(){
        return stockRepository.findAll().stream().map(StockDTO::fromEntity).toList();

    }

}
