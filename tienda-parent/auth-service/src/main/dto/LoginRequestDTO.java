package com.auth_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    
    @NotBlank(message = "Email obligatorio")
    @Email(message = "Formato email inválido")
    private String email;

    @NotBlank(message = "Password obligatorio")
    private String password;
}
