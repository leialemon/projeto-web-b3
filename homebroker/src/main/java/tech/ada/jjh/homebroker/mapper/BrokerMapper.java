package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.BrokerDTO;
import tech.ada.jjh.homebroker.model.Broker;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrokerMapper {
    Broker toEntity(BrokerDTO brokerDTO);
    BrokerDTO toDto(Broker broker);
    List<BrokerDTO> listToDto(List<Broker> brokers);
}
