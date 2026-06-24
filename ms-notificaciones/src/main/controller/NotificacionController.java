package com.ms_notificaciones.controller;

import com.ms_notificaciones.dto.*;
import com.ms_notificaciones.service.NotifiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
@Slf4j
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> createNotification(
            @Valid @RequestBody NotificacionRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.createNotification(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> getAllNotifications() {

        return ResponseEntity.ok(
                notificacionService.getAllNotifications());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<NotificacionResponseDTO>>
    getNotificationsByUser(@PathVariable Long userId) {

        return ResponseEntity.ok(
                notificacionService.getNotificationsByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteNotification(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificacionService.deleteNotification(id));
    }
}