package com.auth_service.dto;

import com.auth_service.model.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 100)
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private Role role;
}
