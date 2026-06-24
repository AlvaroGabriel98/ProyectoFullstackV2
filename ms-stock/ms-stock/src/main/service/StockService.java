package com.ms_stock.service;

import com.ms_stock.model.Stock;
import com.ms_stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock obtenerPorProductoId(Long productoId) {
        log.info("Buscando stock para el producto ID: {}", productoId);
        return stockRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para el producto: " + productoId));
    }

    public boolean tieneStock(Long productoId) {
        Stock stock = obtenerPorProductoId(productoId);
        return stock.getCantidad() > 0;
    }

    public void descontarStock(Long productoId, Integer cantidad) {
        Stock stock = obtenerPorProductoId(productoId);
        
        if (stock.getCantidad() < cantidad) {
            log.error("Error: Stock insuficiente para el producto {}", productoId);
            throw new RuntimeException("No hay suficiente stock disponible");
        }

        stock.setCantidad(stock.getCantidad() - cantidad);
        stockRepository.save(stock);
        log.info("Stock descontado. Nuevo saldo: {}", stock.getCantidad());
    }
}

