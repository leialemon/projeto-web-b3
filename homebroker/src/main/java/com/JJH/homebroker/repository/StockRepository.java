package com.JJH.homebroker.repository;

import com.JJH.homebroker.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
