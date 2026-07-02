package com.user_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotNull(message = "authUserId obligatorio")
    private Long authUserId;

    @NotBlank(message = "Nombre obligatorio")
    private String firstName;

    @NotBlank(message = "Apellido obligatorio")
    private String lastName;

    @NotBlank(message = "Teléfono obligatorio")
    @Size(min = 8, max = 20)
    private String phone;

    @NotBlank(message = "Dirección obligatoria")
    private String address;

    @NotBlank(message = "Ciudad obligatoria")
    private String city;

    @NotBlank(message = "País obligatorio")
    private String country;
}
