
package com.ms_pago.exception;

import com.ms_pago.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PagoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarPagoNoEncontrado(
            PagoNoEncontradoException ex,
            HttpServletRequest request
    ) {
        return construirRespuesta(HttpStatus.NOT_FOUND, "Pago no encontrado", ex.getMessage(), request);
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> manejarReservaNoEncontrada(
            ReservaNoEncontradaException ex,
            HttpServletRequest request
    ) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Reserva no encontrada", ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValidaciones(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> validaciones = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                validaciones.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponseDTO respuesta = ErrorResponseDTO.builder()
                .fecha(LocalDateTime.now())
                .estado(HttpStatus.BAD_REQUEST.value())
                .error("Error de validación")
                .mensaje("Existen campos inválidos en la solicitud")
                .ruta(request.getRequestURI())
                .validaciones(validaciones)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarErrorGeneral(
            Exception ex,
            HttpServletRequest request
    ) {
        return construirRespuesta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno",
                ex.getMessage(),
                request
        );
    }

    private ResponseEntity<ErrorResponseDTO> construirRespuesta(
            HttpStatus estado,
            String error,
            String mensaje,
            HttpServletRequest request
    ) {
        ErrorResponseDTO respuesta = ErrorResponseDTO.builder()
                .fecha(LocalDateTime.now())
                .estado(estado.value())
                .error(error)
                .mensaje(mensaje)
                .ruta(request.getRequestURI())
                .validaciones(null)
                .build();

        return ResponseEntity.status(estado).body(respuesta);
    }
}