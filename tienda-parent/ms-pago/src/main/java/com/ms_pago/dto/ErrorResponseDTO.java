
package com.ms_pago.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {

    private LocalDateTime fecha;
    private int estado;
    private String error;
    private String mensaje;
    private String ruta;
    private Map<String, String> validaciones;
}