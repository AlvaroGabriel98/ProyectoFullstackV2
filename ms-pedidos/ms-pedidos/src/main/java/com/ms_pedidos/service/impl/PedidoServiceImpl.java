package com.ms_pedidos.service.impl;

import com.ms_pedidos.dto.*;
import com.ms_pedidos.model.*;
import com.ms_pedidos.exception.*;
import com.ms_pedidos.repository.PedidoRepository;
import com.ms_pedidos.service.interfaces.PedidosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoServiceImpl implements PedidosService {
    private final PedidoRepository pedidoRepository;

    @Override
    public PedidoResponseDTO createPedido(PedidoRequestDTO request) {

        log.info("Creando pedido usuario {}", request.getUserId());

        BigDecimal total = BigDecimal.ZERO;

        Pedidos pedido = Pedidos.builder()
                .userId(request.getUserId())
                .estado(EstadoPedido.PENDIENTE)
                .active(true)
                .build();

        List<PedidosDetalle> detalles = request.getDetalles()
                .stream()
                .map(detalleDTO -> {

                    BigDecimal subtotal = detalleDTO.getUnitPrice()
                            .multiply(BigDecimal.valueOf(detalleDTO.getQuantity()));

                    PedidosDetalle detalle = PedidosDetalle.builder()
                            .pedido(pedido)
                            .productId(detalleDTO.getProductId())
                            .quantity(detalleDTO.getQuantity())
                            .unitPrice(detalleDTO.getUnitPrice())
                            .subtotal(subtotal)
                            .build();

                    return detalle;
                })
                .toList();

        for(PedidosDetalle detalle : detalles) {
            total = total.add(detalle.getSubtotal());
        }

        pedido.setTotal(total);
        pedido.setDetalles(detalles);

        pedidoRepository.save(pedido);

        log.info("Pedido creado ID {}", pedido.getId());

        return mapToDTO(pedido);
    }

    @Override
    public List<PedidoResponseDTO> getAllPedidos() {

        log.info("Listando pedidos");

        return pedidoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PedidoResponseDTO getPedidoById(Long id) {

        log.info("Buscando pedido ID {}", id);

        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        return mapToDTO(pedido);
    }

    @Override
    public PedidoResponseDTO updateEstado(Long id, String estado) {

        log.info("Actualizando estado pedido {}", id);

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        pedido.setEstado(EstadoPedido.valueOf(estado.toUpperCase()));

        pedidoRepository.save(pedido);

        return mapToDTO(pedido);
    }

    @Override
    public MessageResponseDTO deletePedido(Long id) {

        log.info("Cancelando pedido {}", id);

        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        pedido.setActive(false);
        pedido.setEstado(EstadoPedido.CANCELADO);

        pedidoRepository.save(pedido);

        return new MessageResponseDTO("Pedido cancelado correctamente");
    }

    private PedidoResponseDTO mapToDTO(Pedidos pedido) {

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .userId(pedido.getUserId())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .active(pedido.getActive())
                .detalles(
                        pedido.getDetalles()
                                .stream()
                                .map(detalle -> new PedidoDetalleDTO(
                                        detalle.getProductId(),
                                        detalle.getQuantity(),
                                        detalle.getUnitPrice()
                                ))
                                .toList()
                )
                .build();
    }
}
