package tech.ada.jjh.homebroker.service.create;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.mapper.StockModelMapper;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

@Service
public class CreateStockService{
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public CreateStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
        this.stockMapper = Mappers.getMapper(StockMapper.class);

    }

    public StockDTO execute(StockDTO stock){
        var entity = StockModelMapper.toEntity(stock);
        entity = stockRepository.save(entity);
        return StockModelMapper.toDto(entity);

    }

}
