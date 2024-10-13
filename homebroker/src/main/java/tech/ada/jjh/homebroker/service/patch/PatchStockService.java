package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.exceptions.EntityNotFoundException;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

import java.math.BigDecimal;

@Service
public class PatchStockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public PatchStockService(StockRepository stockRepository, StockMapper stockMapper){
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    public StockDTO alterStockPrice(String ticker, BigDecimal newPrice){
        Stock stock = stockRepository.findByTicker(ticker).orElseThrow(EntityNotFoundException::new);
        stock.setPrice(newPrice);
        return stockMapper.toDto(stockRepository.save(stock));
    }
}
