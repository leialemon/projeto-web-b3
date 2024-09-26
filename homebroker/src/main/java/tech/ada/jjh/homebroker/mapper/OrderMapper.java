package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.factory.Mappers;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.model.Order;

public interface OrderMapper{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(Order order);

    Order toEntity(OrderDTO orderDTO);

}
