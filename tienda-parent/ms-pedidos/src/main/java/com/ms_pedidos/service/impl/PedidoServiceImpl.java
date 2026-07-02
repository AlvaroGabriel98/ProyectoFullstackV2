package com.ms_pedidos.service.impl;

import com.ms_pedidos.dto.MessageResponseDTO;
import com.ms_pedidos.dto.PedidoDetalleDTO;
import com.ms_pedidos.dto.PedidoRequestDTO;
import com.ms_pedidos.dto.PedidoResponseDTO;
import com.ms_pedidos.exception.ResourceNotFoundException;
import com.ms_pedidos.model.EstadoPedido;
import com.ms_pedidos.model.Pedidos;
import com.ms_pedidos.model.PedidosDetalle;
import com.ms_pedidos.repository.PedidoRepository;
import com.ms_pedidos.service.PedidosService;
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

        Pedidos pedido = Pedidos.builder()
                .userId(request.getUserId())
                .estado(EstadoPedido.PENDIENTE)
                .active(true)
                .build();

        List<PedidosDetalle> detalles = request.getDetalles().stream()
                .map(detalleDTO -> {
                    BigDecimal subtotal = detalleDTO.getUnitPrice()
                            .multiply(BigDecimal.valueOf(detalleDTO.getQuantity()));

                    return PedidosDetalle.builder()
                            .pedido(pedido)
                            .productId(detalleDTO.getProductId())
                            .quantity(detalleDTO.getQuantity())
                            .unitPrice(detalleDTO.getUnitPrice())
                            .subtotal(subtotal)
                            .build();
                })
                .toList();

        BigDecimal total = detalles.stream()
                .map(PedidosDetalle::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setTotal(total);
        pedido.setDetalles(detalles);

        pedidoRepository.save(pedido);
        log.info("Pedido creado ID {}", pedido.getId());

        return mapToDTO(pedido);
    }

    @Override
    public List<PedidoResponseDTO> getAllPedidos() {
        return pedidoRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public PedidoResponseDTO getPedidoById(Long id) {
        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
        return mapToDTO(pedido);
    }

    @Override
    public PedidoResponseDTO updateEstado(Long id, String estado) {
        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        pedido.setEstado(EstadoPedido.valueOf(estado.toUpperCase()));
        pedidoRepository.save(pedido);

        return mapToDTO(pedido);
    }

    @Override
    public MessageResponseDTO deletePedido(Long id) {
        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        pedido.setActive(false);
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedidoRepository.save(pedido);

        return new MessageResponseDTO("Pedido cancelado correctamente");
    }

    private PedidoResponseDTO mapToDTO(Pedidos pedido) {
        List<PedidoDetalleDTO> detalles = pedido.getDetalles() == null
                ? List.of()
                : pedido.getDetalles().stream()
                        .map(detalle -> new PedidoDetalleDTO(
                                detalle.getProductId(),
                                detalle.getQuantity(),
                                detalle.getUnitPrice()
                        ))
                        .toList();

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .userId(pedido.getUserId())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .active(pedido.getActive())
                .detalles(detalles)
                .build();
    }
}
