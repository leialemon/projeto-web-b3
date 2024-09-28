package tech.ada.jjh.homebroker.repository;

import tech.ada.jjh.homebroker.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByCpf(String cpf);
}
