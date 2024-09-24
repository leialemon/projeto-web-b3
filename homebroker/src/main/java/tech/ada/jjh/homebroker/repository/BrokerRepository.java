package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.jjh.homebroker.model.Broker;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
