package com.ms_pago.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PagoResponseDTO {

    private Long idPago;
    private LocalDate fechaPago;
    private BigDecimal monto;
    private String metodo;
    private String estado;
    private Long idReserva;

    private String estadoReserva;
    private String nombreMascota;
    private String nombreServicio;
}