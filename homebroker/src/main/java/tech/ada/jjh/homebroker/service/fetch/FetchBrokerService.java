package tech.ada.jjh.homebroker.service.fetch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.Broker;
import tech.ada.jjh.homebroker.repository.BrokerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FetchBrokerService {
    private final BrokerRepository brokerRepository;

    public FetchBrokerService(BrokerRepository brokerRepository){
        this.brokerRepository = brokerRepository;
    }

    public List<Broker> fetchAll(){
        return brokerRepository.findAll();
    }

    public Optional<Broker> fetchBrokerById(Long id){
        return brokerRepository.findById(id);
    }

    public Optional<Broker> fetchBrokerByName(String name){
        return brokerRepository.findByName(name);
    }
}
