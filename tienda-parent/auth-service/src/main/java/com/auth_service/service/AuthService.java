package com.auth_service.service;

import com.auth_service.dto.*;

import java.util.List;

public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO request);
    MessageResponseDTO login(LoginRequestDTO request);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, RegisterRequestDTO request);
    MessageResponseDTO deleteUser(Long id);
}
