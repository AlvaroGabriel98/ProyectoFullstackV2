package com.ms_envios.controller;

import com.ms_envios.model.Envio;
import com.ms_envios.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/envios", "/api/v1/envios"})
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.getEnvios());
    }

    @PostMapping
    public ResponseEntity<Envio> agregarEnvio(@Valid @RequestBody Envio envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.saveEnvio(envio));
    }

    @GetMapping("/{envioId}")
    public ResponseEntity<Envio> buscarEnvio(@PathVariable Long envioId) {
        return ResponseEntity.ok(envioService.getEnvioId(envioId));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Envio>> buscarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(envioService.getEnviosPorCliente(idCliente));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Envio>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(envioService.getEnviosPorEstado(estado));
    }

    @PutMapping("/{envioId}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long envioId, @Valid @RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.updateEnvio(envioId, envio));
    }

    @PatchMapping("/{envioId}/estado")
    public ResponseEntity<Envio> cambiarEstado(@PathVariable Long envioId, @RequestParam String estado) {
        return ResponseEntity.ok(envioService.cambiarEstado(envioId, estado));
    }

    @DeleteMapping("/{envioId}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long envioId) {
        envioService.deleteEnvio(envioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> obtenerTotalEnvios() {
        return ResponseEntity.ok(Map.of("total", envioService.totalEnvios()));
    }
}
