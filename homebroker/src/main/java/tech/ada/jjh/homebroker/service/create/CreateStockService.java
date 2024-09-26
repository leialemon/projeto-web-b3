package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.repository.StockRepository;

@Service
public class CreateStockService{
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public CreateStockService(StockRepository stockRepository, StockMapper stockMapper){
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;

    }

    public StockDTO execute(StockDTO stock){
        var entity = stockMapper.toEntity(stock);
        entity = stockRepository.save(entity);
        return stockMapper.toDto(entity);

    }

}
