package com.auth_service.repository;

import com.auth_service.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
