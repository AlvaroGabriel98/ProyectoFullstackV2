package com.auth_service.service;

import com.ms_auth.dto.AuthRequest;
import com.ms_auth.model.AuthUser;
import com.ms_auth.repository.AuthUserRepository;
import com.ms_auth.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    public String login(AuthRequest request) {
        log.info("Iniciando proceso de login para el usuario: {}", request.getEmail());

        // 1. Buscar el usuario en la base de datos (Regla de negocio)
        AuthUser user = authUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("Fallo de login: El email {} no existe", request.getEmail());
                    return new AuthException("Usuario no encontrado");
                });

        // 2. Validar contraseña (En un proyecto real usarías BCrypt)
        if (!user.getPassword().equals(request.getPassword())) {
            log.warn("Fallo de login: Contraseña incorrecta para el email {}", request.getEmail());
            throw new AuthException("Credenciales inválidas");
        }

        // 3. Generar respuesta (Aquí podrías generar un JWT real)
        log.info("Login exitoso para el usuario: {}. Generando token...", request.getEmail());
        return "TOKEN-SIMULADO-PARA-" + user.getEmail().toUpperCase();
    }
}