package com.ms_notificaciones.service.impl;

import com.ms_notificaciones.dto.*;
import com.ms_notificaciones.exception.ResourceNotFoundException;
import com.ms_notificaciones.model.Notificaciones;
import com.ms_notificaciones.repository.NotificacionRepository;
import com.ms_notificaciones.service.NotifiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotifiServiceImpl implements NotifiService {

    private final NotificacionRepository repository;

    @Override
    public NotificacionResponseDTO createNotification(NotificacionRequestDTO request) {

        log.info("Creando notificación para usuario {}", request.getUserId());

        Notificaciones notification = Notificaciones.builder()
                .userId(request.getUserId())
                .titulo(request.getTitulo())
                .mensaje(request.getMensaje())
                .tipo(request.getTipo())
                .leida(false)
                .active(true)
                .build();

        repository.save(notification);

        log.info("Notificación creada correctamente. ID: {}", notification.getId());

        return mapToDTO(notification);
    }

    @Override
    public List<NotificacionResponseDTO> getAllNotifications() {

        log.info("Listando todas las notificaciones");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<NotificacionResponseDTO> getNotificationsByUser(Long userId) {

        log.info("Buscando notificaciones del usuario {}", userId);

        return repository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public NotificacionResponseDTO markAsRead(Long id) {

        log.info("Marcando como leída la notificación {}", id);

        Notificaciones notification = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("No existe la notificación {}", id);
                    return new ResourceNotFoundException("Notificación no encontrada");
                });

        notification.setLeida(true);

        repository.save(notification);

        log.info("Notificación {} marcada como leída", id);

        return mapToDTO(notification);
    }

    @Override
    public MessageResponseDTO deleteNotification(Long id) {

        log.info("Deshabilitando notificación {}", id);

        Notificaciones notification = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("No existe la notificación {}", id);
                    return new ResourceNotFoundException("Notificación no encontrada");
                });

        notification.setActive(false);

        repository.save(notification);

        log.info("Notificación {} deshabilitada", id);

        return new MessageResponseDTO("Notificación eliminada correctamente");
    }

    private NotificacionResponseDTO mapToDTO(Notificaciones notification) {

        return NotificacionResponseDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .titulo(notification.getTitulo())
                .mensaje(notification.getMensaje())
                .tipo(notification.getTipo())
                .leida(notification.getLeida())
                .active(notification.getActive())
                .build();
    }
}