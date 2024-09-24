package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.jjh.homebroker.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
