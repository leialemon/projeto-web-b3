package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Portfolio;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
