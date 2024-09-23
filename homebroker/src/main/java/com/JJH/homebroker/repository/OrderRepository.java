package com.JJH.homebroker.repository;

import com.JJH.homebroker.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
