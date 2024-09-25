package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.jjh.homebroker.model.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
}
