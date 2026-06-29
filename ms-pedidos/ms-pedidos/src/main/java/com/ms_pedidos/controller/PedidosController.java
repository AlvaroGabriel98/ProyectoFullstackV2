package com.ms_pedidos.controller;

import com.ms_pedidos.dto.*;
import com.ms_pedidos.service.PedidosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidosController {
   private final PedidosService pedidosService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> createPedido(
            @Valid @RequestBody PedidoRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidosService.createPedido(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAllPedidos() {

        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deletePedido(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoService.deletePedido(id));
    }
}
