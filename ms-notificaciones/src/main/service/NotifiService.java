package com.ms_notificaciones.service;

import com.ms_notificaciones.model.Notificacion;
import com.ms_notificaciones.repository.NotificacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class NotifiService {

    @Autowired
    private NotifiRepository notificacionRepository;

    public Notificacion enviarNotificacion(Notificacion notificacion) {
        log.info("Procesando envío de notificación para: {}", notificacion.getDestinatario());

        if (notificacion.getMensaje().contains("error")) {
            log.error("Se detectó un mensaje de error crítico para {}", notificacion.getDestinatario());
        }

        Notificacion guardada = notificacionRepository.save(notificacion);
        log.info("Notificación guardada en historial con ID: {}", guardada.getId());
        
        return guardada;
    }

    public List<Notificacion> obtenerHistorial() {
        return notificacionRepository.findAll();
    }
}