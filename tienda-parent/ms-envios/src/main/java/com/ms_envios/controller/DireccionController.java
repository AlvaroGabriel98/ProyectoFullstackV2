package com.ms_envios.controller;

import com.ms_envios.model.Direccion;
import com.ms_envios.service.DireccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<Direccion>> listar() {
        return ResponseEntity.ok(direccionService.listar());
    }

    @PostMapping
    public ResponseEntity<Direccion> crear(@Valid @RequestBody Direccion direccion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.crear(direccion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(direccionService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        direccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
