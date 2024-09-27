package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.BrokerDTO;
import tech.ada.jjh.homebroker.mapper.BrokerMapper;
import tech.ada.jjh.homebroker.model.Broker;
import tech.ada.jjh.homebroker.repository.BrokerRepository;

import java.util.ArrayList;

@Service
public class CreateBrokerService {

    private final BrokerRepository brokerRepository;
    private final BrokerMapper brokerMapper;

    public CreateBrokerService(BrokerRepository brokerRepository, BrokerMapper brokerMapper){
        this.brokerRepository = brokerRepository;
        this.brokerMapper = brokerMapper;
    }

    public BrokerDTO createBroker(BrokerDTO broker){
        broker.setFees(new ArrayList<>());
        var entity = brokerMapper.toEntity(broker);
        brokerRepository.save(entity);
        return brokerMapper.toDto(entity);
    }
}
