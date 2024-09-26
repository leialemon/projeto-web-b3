package tech.ada.jjh.homebroker.mapper;

import org.modelmapper.ModelMapper;
import tech.ada.jjh.homebroker.dto.OrderDTO;
import tech.ada.jjh.homebroker.model.Order;

public class OrderModelMapper{
    private static final ModelMapper modelMapper = new ModelMapper();

    public static OrderDTO toDto(Order order){
        return modelMapper.map(order, OrderDTO.class);

    }

    public static Order toEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);

    }

}
