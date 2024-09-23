package com.JJH.homebroker.repository;

import com.JJH.homebroker.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
