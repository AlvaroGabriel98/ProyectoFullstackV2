package com.user_service.service.impl;

import com.gaming.user_service.dto.*;
import com.gaming.user_service.model.User;
import com.gaming.user_service.exception.*;
import com.gaming.user_service.repository.UserRepository;
import com.gaming.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {
    private final UserProfileRepository repository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {

        log.info("Creando perfil usuario auth ID: {}", request.getAuthUserId());

        if(repository.existsByAuthUserId(request.getAuthUserId())) {
            throw new BusinessException("El perfil ya existe");
        }

        UserProfile user = UserProfile.builder()
                .authUserId(request.getAuthUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .active(true)
                .build();

        repository.save(user);

        log.info("Perfil creado ID: {}", user.getId());

        return mapToDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        log.info("Listando perfiles usuarios");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        log.info("Buscando perfil ID: {}", id);

        UserProfile user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado"));

        return mapToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {

        log.info("Actualizando perfil ID: {}", id);

        UserProfile user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());

        repository.save(user);

        log.info("Perfil actualizado ID: {}", id);

        return mapToDTO(user);
    }

    @Override
    public MessageResponseDTO deleteUser(Long id) {

        log.info("Deshabilitando perfil ID: {}", id);

        UserProfile user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado"));

        user.setActive(false);

        repository.save(user);

        return new MessageResponseDTO("Perfil deshabilitado correctamente");
    }

    private UserResponseDTO mapToDTO(UserProfile user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .authUserId(user.getAuthUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .city(user.getCity())
                .country(user.getCountry())
                .active(user.getActive())
                .build();
    }
}
