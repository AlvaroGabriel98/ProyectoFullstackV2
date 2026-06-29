package com.ms_pago.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms_pago.dto.PagoRequestDTO;
import com.ms_pago.dto.PagoResponseDTO;
import com.ms_pago.service.PagoService;

@Slf4j
@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        log.info("Solicitud GET /api/v1/pagos");
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("Solicitud GET /api/v1/pagos/{}", id);
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<?> listarPorReserva(@PathVariable Long idReserva) {
        log.info("Solicitud GET /api/v1/pagos/reserva/{}", idReserva);
        return ResponseEntity.ok(pagoService.listarPorReserva(idReserva));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarPorEstado(@PathVariable String estado) {
        log.info("Solicitud GET /api/v1/pagos/estado/{}", estado);
        return ResponseEntity.ok(pagoService.listarPorEstado(estado));
    }

    @GetMapping("/metodo/{metodo}")
    public ResponseEntity<?> listarPorMetodo(@PathVariable String metodo) {
        log.info("Solicitud GET /api/v1/pagos/metodo/{}", metodo);
        return ResponseEntity.ok(pagoService.listarPorMetodo(metodo));
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crear(@Valid @RequestBody PagoRequestDTO request) {
        log.info("Solicitud POST /api/v1/pagos");
        PagoResponseDTO respuesta = pagoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PagoRequestDTO request
    ) {
        log.info("Solicitud PUT /api/v1/pagos/{}", id);
        return ResponseEntity.ok(pagoService.actualizar(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    ) {
        log.info("Solicitud PATCH /api/v1/pagos/{}/estado?estado={}", id, estado);
        return ResponseEntity.ok(pagoService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("Solicitud DELETE /api/v1/pagos/{}", id);
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}