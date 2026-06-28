package com.ms_stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms_stock.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
    Optional<Stock> findByProductoId(Long productoId);
    
    boolean existsByProductoId(Long productoId);
}
