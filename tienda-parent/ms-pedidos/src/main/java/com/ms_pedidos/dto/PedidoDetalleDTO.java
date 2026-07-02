package com.ms_pedidos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalleDTO {

    @NotNull(message = "productId obligatorio")
    private Long productId;

    @NotNull(message = "quantity obligatoria")
    @Positive(message = "quantity debe ser positiva")
    private Integer quantity;

    @NotNull(message = "unitPrice obligatorio")
    @DecimalMin(value = "0.01", message = "unitPrice debe ser mayor a cero")
    private BigDecimal unitPrice;
}
