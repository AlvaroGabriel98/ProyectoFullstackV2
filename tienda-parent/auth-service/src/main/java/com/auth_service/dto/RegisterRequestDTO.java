package com.auth_service.dto;

import com.auth_service.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 100, message = "El username debe tener entre 4 y 100 caracteres")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato email invalido")
    private String email;

    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 8, message = "La contrasena debe tener minimo 8 caracteres")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private Role role;
}
