package com.ms_pedidos.dto;

import com.gaming.mspedidos.entity.EstadoPedido;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
    private Long id;
    private Long userId;
    private BigDecimal total;
    private EstadoPedido estado;
    private Boolean active;
    private List<PedidoDetalleDTO> detalles;
}
