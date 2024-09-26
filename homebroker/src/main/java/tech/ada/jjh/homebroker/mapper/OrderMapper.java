package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.model.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper{

    OrderDTO toDto(Order order);
    Order toEntity(OrderDTO orderDTO);
}
