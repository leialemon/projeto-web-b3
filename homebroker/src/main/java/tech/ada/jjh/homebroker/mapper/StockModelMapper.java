package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.model.Stock;

@Mapper
public class StockModelMapper{
    private static final ModelMapper modelMapper = new ModelMapper();

    public static StockDTO toDto(Stock stock){
        return modelMapper.map(stock, StockDTO.class);

    }

    public static Stock toEntity(StockDTO stockDTO){
        return modelMapper.map(stockDTO, Stock.class);

    }

}
