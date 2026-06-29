package com.ms_pago.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pago  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @NotNull(message = "La fecha de pago es obligatoria")
    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "1.0", message = "El monto debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 30, message = "El método no puede superar los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String metodo;

    @NotBlank(message = "El estado del pago es obligatorio")
    @Size(max = 30, message = "El estado no puede superar los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String estado;

    @NotNull(message = "El ID de la reserva es obligatorio")
    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;
}