package com.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; 

    @Column(nullable = false)
    private String password;

    private String role; 
}