package com.ms_notificaciones.service;

import com.ms_notificaciones.dto.*;
import com.ms_notificaciones.model.Notificaciones;
import com.ms_notificaciones.exception.*;
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

        log.info("Creando notificación usuario {}", request.getUserId());

        Notificacion notification = Notificacion.builder()
                .userId(request.getUserId())
                .titulo(request.getTitulo())
                .mensaje(request.getMensaje())
                .tipo(request.getTipo())
                .leida(false)
                .active(true)
                .build();

        repository.save(notification);

        log.info("Notificación creada ID {}", notification.getId());

        return mapToDTO(notification);
    }

    @Override
    public List<NotificacionResponseDTO> getAllNotifications() {

        log.info("Listando notificaciones");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<NotificacionResponseDTO> getNotificationsByUser(Long userId) {

        log.info("Buscando notificaciones usuario {}", userId);

        return repository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public NotificacionResponseDTO markAsRead(Long id) {

        log.info("Marcando notificación {} como leída", id);

        Notificacion notification = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notificación no encontrada"));

        notification.setLeida(true);

        repository.save(notification);

        return mapToDTO(notification);
    }

    @Override
    public MessageResponseDTO deleteNotification(Long id) {

        log.info("Deshabilitando notificación {}", id);

        Notificacion notification = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notificación no encontrada"));

        notification.setActive(false);

        repository.save(notification);

        return new MessageResponseDTO("Notificación eliminada correctamente");
    }

    private NotificacionResponseDTO mapToDTO(Notificacion notification) {

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