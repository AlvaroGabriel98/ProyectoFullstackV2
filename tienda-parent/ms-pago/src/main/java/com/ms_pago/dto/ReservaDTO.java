package com.ms_pago.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaDTO {

    private Long idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String observacion;
    private Long idMascota;
    private Long idServicio;

    private String nombreMascota;
    private String nombreServicio;
    private String precioServicio;
}