package com.ms_stock.service;

import com.ms_stock.dto.*;

import java.util.List;

public interface StockService {

    StockResponseDTO createStock(StockRequestDTO request);

    List<StockResponseDTO> getAllStock();

    StockResponseDTO getStockById(Long id);

    StockResponseDTO updateStock(Long id, StockRequestDTO request);

    MessageResponseDTO deleteStock(Long id);

    MessageResponseDTO reduceStock(Long productId, Integer cantidad);

}