package com.ms_pago.exception;

public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(Long id) {
        super("No se encontró la reserva con ID: " + id);
    }
}