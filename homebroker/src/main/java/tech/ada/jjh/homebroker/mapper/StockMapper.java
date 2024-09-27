package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.model.Stock;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper{

    StockDTO toDto(Stock stock);

    Stock toEntity(StockDTO pessoaDTO);

    List<StockDTO> listToDto(List<Stock> stocks);

}
