package com.ms_stock.service.impl;

import com.ms_stock.dto.*;
import com.ms_stock.model.Stock;
import com.ms_stock.exception.*;
import com.ms_stock.repository.StockRepository;
import com.ms_stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    
    private final StockRepository repository;

    @Override
    public StockResponseDTO createStock(StockRequestDTO request) {

        log.info("Creando stock para producto: {}", request.getProductId());

        if(repository.existsByProductId(request.getProductId())) {
            throw new BusinessException("El producto ya tiene stock");
        }

        Stock stockEntity = Stock.builder()
                .productId(request.getProductId())
                .stock(request.getStock())
                .reservedStock(request.getReservedStock())
                .minimumStock(request.getMinimumStock())
                .active(true)
                .build();

        repository.save(stockEntity);

        log.info("Stock creado ID: {}", stockEntity.getId());

        return mapToDTO(stockEntity);
    }

    @Override
    public List<StockResponseDTO> getAllStock() {

        log.info("Listando stock");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public StockResponseDTO getStockById(Long id) {

        log.info("Buscando stock ID: {}", id);

        Stock stockEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado"));

        return mapToDTO(stockEntity);
    }

    @Override
    public StockResponseDTO updateStock(Long id, StockRequestDTO request) {

        log.info("Actualizando stock ID: {}", id);

        Stock stockEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado"));

        stockEntity.setStock(request.getStock());
        stockEntity.setReservedStock(request.getReservedStock());
        stockEntity.setMinimumStock(request.getMinimumStock());

        repository.save(stockEntity);

        log.info("Stock actualizado ID: {}", id);

        return mapToDTO(stockEntity);
    }

    @Override
    public MessageResponseDTO deleteStock(Long id) {

        log.info("Deshabilitando stock ID: {}", id);

        Stock stockEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado"));

        stockEntity.setActive(false);

        repository.save(stockEntity);

        return new MessageResponseDTO("Stock deshabilitado correctamente");
    }

    @Override
    public MessageResponseDTO reduceStock(Long productId, Integer quantity) {

        log.info("Reduciendo stock producto {} cantidad {}", productId, quantity);

        Stock stockEntity = repository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en stock"));

        if(stockEntity.getStock() < quantity) {
            log.error("Stock insuficiente producto {}", productId);
            throw new BusinessException("Stock insuficiente");
        }

        stockEntity.setStock(stockEntity.getStock() - quantity);

        repository.save(stockEntity);

        log.info("Stock actualizado producto {}", productId);

        return new MessageResponseDTO("Stock actualizado correctamente");
    }

    private StockResponseDTO mapToDTO(Stock stockEntity) {

        return StockResponseDTO.builder()
                .id(stockEntity.getId())
                .productId(stockEntity.getProductId())
                .stock(stockEntity.getStock())
                .reservedStock(stockEntity.getReservedStock())
                .minimumStock(stockEntity.getMinimumStock())
                .active(stockEntity.getActive())
                .build();
    }
}