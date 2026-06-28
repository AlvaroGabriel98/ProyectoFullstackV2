package com.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {
    @NotNull(message = "userId obligatorio")
    private Long userId;

    @NotEmpty(message = "Debe incluir productos")
    private List<PedidoDetalleDTO> detalles;
}
