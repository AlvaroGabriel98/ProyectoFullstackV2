package com.ms_notificaciones.controller;

import com.ms_notificaciones.dto.*;
import com.ms_notificaciones.service.NotifiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
@Slf4j
public class NotificacionController {

    private final NotifiService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> createNotification(
            @Valid @RequestBody NotificacionRequestDTO request) {

        log.info("POST /notificaciones ejecutado");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.createNotification(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> getAllNotifications() {

        log.info("GET /notificaciones ejecutado");

        return ResponseEntity.ok(
                notificacionService.getAllNotifications());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<NotificacionResponseDTO>> getNotificationsByUser(
            @PathVariable Long userId) {

        log.info("GET /notificaciones/usuario/{} ejecutado", userId);

        return ResponseEntity.ok(
                notificacionService.getNotificationsByUser(userId));
    }

    @PutMapping("/{id}/leida")
    public ResponseEntity<NotificacionResponseDTO> markAsRead(
            @PathVariable Long id) {

        log.info("PUT /notificaciones/{}/leida ejecutado", id);

        return ResponseEntity.ok(
                notificacionService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteNotification(
            @PathVariable Long id) {

        log.info("DELETE /notificaciones/{} ejecutado", id);

        return ResponseEntity.ok(
                notificacionService.deleteNotification(id));
    }
}