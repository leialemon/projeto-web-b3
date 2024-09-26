package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.factory.Mappers;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.model.Stock;

public interface StockMapper{
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDTO toDto(Stock stock);

    Stock toEntity(StockDTO pessoaDTO);

}
