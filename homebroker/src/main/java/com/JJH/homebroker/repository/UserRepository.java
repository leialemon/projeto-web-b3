package com.JJH.homebroker.repository;

import com.JJH.homebroker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
