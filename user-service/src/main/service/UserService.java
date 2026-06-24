package com.user_service.repository;

import com.user_service.model.User;
import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO request);

    MessageResponseDTO deleteUser(Long id);
}
