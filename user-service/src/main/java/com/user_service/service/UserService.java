package com.user_service.service;

import com.user_service.dto.*;
import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO request);

    MessageResponseDTO deleteUser(Long id);
}
