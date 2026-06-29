package com.ms_pago.client;

import com.ms_pago.dto.ReservaDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface ReservaClient {
      @GetMapping("/api/v1/reservas/{id}")
    ReservaDTO buscarReservaPorId(@PathVariable("id") Long id);

}
