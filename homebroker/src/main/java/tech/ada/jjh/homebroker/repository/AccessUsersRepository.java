package tech.ada.jjh.homebroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.jjh.homebroker.model.AccessUser;

import java.util.Optional;

@Repository
public interface AccessUsersRepository extends JpaRepository<AccessUser, Long> {
    Optional<AccessUser> findByUsername(String username);
}
