package com.ms_notificaciones.service;

import com.ms_notificaciones.dto.*;
import java.util.List;

public interface NotifiService {

    NotificacionResponseDTO createNotification(NotificacionRequestDTO request);

    List<NotificacionResponseDTO> getAllNotifications();

    List<NotificacionResponseDTO> getNotificationsByUser(Long userId);

    NotificacionResponseDTO markAsRead(Long id);

    MessageResponseDTO deleteNotification(Long id);

}