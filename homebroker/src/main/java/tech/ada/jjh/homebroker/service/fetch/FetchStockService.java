package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FetchStockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public FetchStockService(StockRepository stockRepository, StockMapper stockMapper){
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    public Optional<StockDTO> fetchById(Long id){
        return stockRepository.findById(id).map(stockMapper::toDto);
    }

    public Optional<StockDTO> fetchByTicker(String ticker){
        return stockRepository.findByTicker(ticker).map(stockMapper::toDto);
    }

    public List<StockDTO> fetchAll(){
        return stockMapper.listToDto(stockRepository.findAll());
    }
}
