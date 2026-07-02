package com.ms_notificaciones.dto;

import com.ms_notificaciones.model.TipoNotificacion;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionResponseDTO {

    private Long id;
    private Long userId;
    private String titulo;
    private String mensaje;
    private TipoNotificacion tipo;
    private Boolean leida;
    private Boolean active;
}