package com.auth_service.controller;

import com.gaming.authservice.dto.*;
import com.ms_auth.dto.AuthRequest;
import com.ms_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private AuthService authService;

    
   public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        log.info("POST /auth/register ejecutado");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        log.info("POST /auth/login ejecutado");

        return ResponseEntity.ok(authService.login(request));
    }

     @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        log.info("GET /auth/users/{} ejecutado", id);

        return ResponseEntity.ok(authService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody RegisterRequestDTO request) {

        log.info("PUT /auth/users/{} ejecutado", id);

        return ResponseEntity.ok(authService.updateUser(id, request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<MessageResponseDTO> deleteUser(@PathVariable Long id) {

        log.info("DELETE /auth/users/{} ejecutado", id);

        return ResponseEntity.ok(authService.deleteUser(id));
    }
}