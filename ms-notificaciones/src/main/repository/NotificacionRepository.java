package com.ms_notificaciones.repository;

import com.gaming.msnotificaciones.model.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificaciones, Long> {

    List<Notificaciones> findByUserId(Long userId);
}