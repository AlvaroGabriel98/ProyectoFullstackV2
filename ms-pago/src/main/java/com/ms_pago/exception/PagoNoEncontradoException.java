package com.ms_pago.exception;

public class PagoNoEncontradoException extends RuntimeException {

    public PagoNoEncontradoException(Long id) {
        super("No se encontró el pago con ID: " + id);
    }
}