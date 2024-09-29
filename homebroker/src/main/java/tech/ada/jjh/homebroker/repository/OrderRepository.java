package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.jjh.homebroker.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Order findByUuid(String uuid);
}
