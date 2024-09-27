package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.model.Order;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper{

    OrderDTOResponse toDto(Order order);
    List<OrderDTOResponse> listToDto(List<Order> orders);

    @Mapping(source = "stockQuantity", target = "stockQuantity")
    @Mapping(target = "stock", ignore = true)
    Order toEntity(OrderDTORequest orderDTORequest);
}
