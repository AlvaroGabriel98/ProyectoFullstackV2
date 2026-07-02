package com.ms_pedidos.controller;

import com.ms_pedidos.dto.*;
import com.ms_pedidos.service.PedidosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidosController {

    private final PedidosService pedidosService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> createPedido(@Valid @RequestBody PedidoRequestDTO request) {
        log.info("POST /api/pedidos ejecutado");
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.createPedido(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAllPedidos() {
        log.info("GET /api/pedidos ejecutado");
        return ResponseEntity.ok(pedidosService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@PathVariable Long id) {
        log.info("GET /api/pedidos/{} ejecutado", id);
        return ResponseEntity.ok(pedidosService.getPedidoById(id));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<PedidoResponseDTO> updateEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        log.info("PATCH /api/pedidos/{}/estado/{} ejecutado", id, estado);
        return ResponseEntity.ok(pedidosService.updateEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deletePedido(@PathVariable Long id) {
        log.info("DELETE /api/pedidos/{} ejecutado", id);
        return ResponseEntity.ok(pedidosService.deletePedido(id));
    }
}
