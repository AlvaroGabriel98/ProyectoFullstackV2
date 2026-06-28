package com.ms_notificaciones.dto;

import com.ms_notificaciones.model.TipoNotificacion;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequestDTO {

    @NotNull(message = "userId obligatorio")
    private Long userId;

    @NotBlank(message = "Título obligatorio")
    private String titulo;

    @NotBlank(message = "Mensaje obligatorio")
    @Size(max = 500)
    private String mensaje;

    @NotNull(message = "Tipo obligatorio")
    private TipoNotificacion tipo;
}