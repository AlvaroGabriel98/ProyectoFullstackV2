package com.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_service.dto.UserRequestDTO;
import com.user_service.dto.UserResponseDTO;
import com.user_service.exception.GlobalExceptionHandler;
import com.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void createUser_deberiaRetornar201CuandoUsuarioEsValido() throws Exception {
        // ARRANGE: preparar datos de entrada y respuesta simulada del service
        UserRequestDTO request = new UserRequestDTO(
                10L,
                "Karla",
                "Tapia",
                "987654321",
                "Av. Principal 123",
                "Santiago",
                "Chile"
        );

        UserResponseDTO response = UserResponseDTO.builder()
                .id(1L)
                .authUserId(10L)
                .firstName("Karla")
                .lastName("Tapia")
                .phone("987654321")
                .address("Av. Principal 123")
                .city("Santiago")
                .country("Chile")
                .active(true)
                .build();

        when(userService.createUser(any(UserRequestDTO.class)))
                .thenReturn(response);

        // ACT + ASSERT: ejecutar endpoint y verificar respuesta HTTP esperada
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.authUserId").value(10L))
                .andExpect(jsonPath("$.firstName").value("Karla"))
                .andExpect(jsonPath("$.lastName").value("Tapia"))
                .andExpect(jsonPath("$.phone").value("987654321"))
                .andExpect(jsonPath("$.address").value("Av. Principal 123"))
                .andExpect(jsonPath("$.city").value("Santiago"))
                .andExpect(jsonPath("$.country").value("Chile"))
                .andExpect(jsonPath("$.active").value(true));

        // VERIFY: comprobar que el controller llamó una sola vez al service
        verify(userService, times(1)).createUser(any(UserRequestDTO.class));
        verifyNoMoreInteractions(userService);

        // Caso hipotético de falla para QA:
        // Si se esperaba HTTP 201 Created y se obtiene HTTP 200 OK,
        // QA debe reportar que el endpoint POST /users no está retornando
        // el estado correcto de creación. Desarrollo debe revisar el
        // ResponseEntity.status(HttpStatus.CREATED) del controller.
    }

    @Test
    void getAllUsers_deberiaRetornar200YListaDeUsuarios() throws Exception {
        // ARRANGE: preparar lista simulada de usuarios que devolverá el service
        UserResponseDTO user1 = UserResponseDTO.builder()
                .id(1L)
                .authUserId(10L)
                .firstName("Karla")
                .lastName("Tapia")
                .phone("987654321")
                .address("Av. Principal 123")
                .city("Santiago")
                .country("Chile")
                .active(true)
                .build();

        UserResponseDTO user2 = UserResponseDTO.builder()
                .id(2L)
                .authUserId(20L)
                .firstName("Ignacio")
                .lastName("Morales")
                .phone("912345678")
                .address("Calle Secundaria 456")
                .city("Valparaiso")
                .country("Chile")
                .active(true)
                .build();

        when(userService.getAllUsers())
                .thenReturn(List.of(user1, user2));

        // ACT + ASSERT: ejecutar endpoint y verificar respuesta HTTP esperada
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].authUserId").value(10L))
                .andExpect(jsonPath("$[0].firstName").value("Karla"))
                .andExpect(jsonPath("$[0].lastName").value("Tapia"))
                .andExpect(jsonPath("$[0].active").value(true))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].authUserId").value(20L))
                .andExpect(jsonPath("$[1].firstName").value("Ignacio"))
                .andExpect(jsonPath("$[1].lastName").value("Morales"))
                .andExpect(jsonPath("$[1].active").value(true));

        // VERIFY: comprobar que el controller llamó una sola vez al service
        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);

        // Caso hipotético de falla para QA:
        // Si se esperaba una lista con 2 usuarios y se obtiene una lista vacía,
        // QA debe reportar que el endpoint GET /users no está retornando los datos esperados.
        // Desarrollo debe revisar el método getAllUsers() del service y su conexión con el repository.
    }
}