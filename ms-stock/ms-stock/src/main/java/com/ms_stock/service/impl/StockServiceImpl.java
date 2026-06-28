package com.ms_stock.service.impl;

import com.ms_stock.dto.MessageResponseDTO;
import com.ms_stock.dto.StockRequestDTO;
import com.ms_stock.dto.StockResponseDTO;
import com.ms_stock.exception.BusinessException;
import com.ms_stock.exception.ResourceNotFoundException;
import com.ms_stock.model.Stock;
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

        log.info("Creando stock para producto: {}", request.getProductoId());

        if (repository.existsByProductoId(request.getProductoId())) {
            throw new BusinessException("El producto ya tiene stock registrado");
        }

        Stock stock = Stock.builder()
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .build();

        repository.save(stock);

        log.info("Stock creado con ID: {}", stock.getId());

        return mapToDTO(stock);
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

        log.info("Buscando producto ID: {}", id);

        Stock stock = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("producto no encontrado"));

        return mapToDTO(stock);
    }

    @Override
    public StockResponseDTO updateStock(Long id, StockRequestDTO request) {

        log.info("Actualizando stock ID: {}", id);

        Stock stock = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado"));

        stock.setProductoId(request.getProductoId());
        stock.setCantidad(request.getCantidad());

        repository.save(stock);

        log.info("Stock actualizado");

        return mapToDTO(stock);
    }

    @Override
    public MessageResponseDTO deleteStock(Long id) {

        log.info("Eliminando stock ID: {}", id);

        Stock stock = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock no encontrado"));

        repository.delete(stock);

        return new MessageResponseDTO("Stock eliminado correctamente");
    }

    @Override
    public MessageResponseDTO reduceStock(Long productoId, Integer cantidad) {

        log.info("Reduciendo stock del producto {}", productoId);

        Stock stock = repository.findByProductoId(productoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado"));

        if (stock.getCantidad() < cantidad) {
            throw new BusinessException("Stock insuficiente");
        }

        stock.setCantidad(stock.getCantidad() - cantidad);

        repository.save(stock);

        return new MessageResponseDTO("Stock actualizado correctamente");
    }

    private StockResponseDTO mapToDTO(Stock stock) {

        return StockResponseDTO.builder()
                .id(stock.getId())
                .productoId(stock.getProductoId())
                .cantidad(stock.getCantidad())
                .build();
    }
}