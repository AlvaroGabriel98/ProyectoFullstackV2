package com.example.user_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private Long authUserId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Boolean active;
}
