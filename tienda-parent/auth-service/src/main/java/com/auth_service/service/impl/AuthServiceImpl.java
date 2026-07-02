package com.auth_service.service.impl;

import com.auth_service.dto.*;
import com.auth_service.exception.BusinessException;
import com.auth_service.exception.ResourceNotFoundException;
import com.auth_service.exception.UnauthorizedException;
import com.auth_service.model.AuthUser;
import com.auth_service.repository.AuthUserRepository;
import com.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {
        log.info("Intentando registrar usuario: {}", request.getEmail());

        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException("El email ya existe");
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new BusinessException("El username ya existe");
        }

        AuthUser user = AuthUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        repository.save(user);
        log.info("Usuario registrado correctamente con ID: {}", user.getId());

        return mapToDTO(user);
    }

    @Override
    public MessageResponseDTO login(LoginRequestDTO request) {
        log.info("Intento login usuario: {}", request.getEmail());

        AuthUser user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenciales invalidas"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new UnauthorizedException("Usuario deshabilitado");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Credenciales invalidas");
        }

        return new MessageResponseDTO("Login exitoso");
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        AuthUser user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return mapToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, RegisterRequestDTO request) {
        AuthUser user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        repository.save(user);
        return mapToDTO(user);
    }

    @Override
    public MessageResponseDTO deleteUser(Long id) {
        AuthUser user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        user.setActive(false);
        repository.save(user);
        return new MessageResponseDTO("Usuario eliminado correctamente");
    }

    private UserResponseDTO mapToDTO(AuthUser user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
}
