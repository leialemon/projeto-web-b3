package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Broker;
import tech.ada.jjh.homebroker.repository.BrokerRepository;

@Service
public class CreateBrokerService {

    private final BrokerRepository brokerRepository;

    public CreateBrokerService(BrokerRepository brokerRepository){
        this.brokerRepository = brokerRepository;
    }

    public Broker createBroker(Broker broker){
        return brokerRepository.save(broker);
    }
}
