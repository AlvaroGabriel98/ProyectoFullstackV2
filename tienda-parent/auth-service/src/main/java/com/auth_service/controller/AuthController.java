package com.auth_service.controller;

import com.auth_service.dto.*;
import com.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        log.info("POST /api/auth/register ejecutado");
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        log.info("POST /api/auth/login ejecutado");
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.info("GET /api/auth/users ejecutado");
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.info("GET /api/auth/users/{} ejecutado", id);
        return ResponseEntity.ok(authService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody RegisterRequestDTO request) {
        log.info("PUT /api/auth/users/{} ejecutado", id);
        return ResponseEntity.ok(authService.updateUser(id, request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<MessageResponseDTO> deleteUser(@PathVariable Long id) {
        log.info("DELETE /api/auth/users/{} ejecutado", id);
        return ResponseEntity.ok(authService.deleteUser(id));
    }
}
