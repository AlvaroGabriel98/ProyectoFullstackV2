package com.auth_service.service.impl;

import com.auth_service.dto.*;
import com.auth_service.model.Auth;
import com.auth_service.exception.*;
import com.auth_service.repository.AuthUserRepository;
import com.auth_service.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserAuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {

        log.info("Intentando registrar usuario: {}", request.getEmail());

        if(repository.existsByEmail(request.getEmail())) {
            log.error("Email ya registrado: {}", request.getEmail());
            throw new BusinessException("El email ya existe");
        }

        if(repository.existsByUsername(request.getUsername())) {
            log.error("Username ya registrado: {}", request.getUsername());
            throw new BusinessException("El username ya existe");
        }

        UserAuth user = UserAuth.builder()
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

        UserAuth user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado: {}", request.getEmail());
                    return new UnauthorizedException("Credenciales inválidas");
                });

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("Password incorrecto para usuario: {}", request.getEmail());
            throw new UnauthorizedException("Credenciales inválidas");
        }

        log.info("Login exitoso usuario: {}", request.getEmail());

        return new MessageResponseDTO("Login exitoso");
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        log.info("Listando usuarios");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        log.info("Buscando usuario ID: {}", id);

        UserAuth user = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado ID: {}", id);
                    return new ResourceNotFoundException("Usuario no encontrado");
                });

        return mapToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, RegisterRequestDTO request) {

        log.info("Actualizando usuario ID: {}", id);

        UserAuth user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        if(request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        repository.save(user);

        log.info("Usuario actualizado ID: {}", id);

        return mapToDTO(user);
    }

    @Override
    public MessageResponseDTO deleteUser(Long id) {

        log.info("Eliminando usuario ID: {}", id);

        UserAuth user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        user.setActive(false);

        repository.save(user);

        log.info("Usuario deshabilitado ID: {}", id);

        return new MessageResponseDTO("Usuario eliminado correctamente");
    }

    private UserResponseDTO mapToDTO(UserAuth user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }

}
