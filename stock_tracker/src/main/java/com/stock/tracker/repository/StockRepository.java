package com.stock.tracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.tracker.model.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {
}
