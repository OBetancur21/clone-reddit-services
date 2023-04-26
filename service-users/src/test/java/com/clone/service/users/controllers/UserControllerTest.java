package com.clone.service.users.controllers;

import com.clone.service.users.dtos.UserDTO;
import com.clone.service.users.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception{
        List<UserDTO> userDTOS = Arrays.asList(
                UserDTO.builder()
                        .id(1L)
                        .email("prueba@prueba.com")
                        .username("prueba")
                        .password("Contraseña1")
                        .photo("photo.png").build(),
                UserDTO.builder()
                        .id(2L)
                        .email("Soyunaprueba@prueba.com")
                        .username("prueba2")
                        .password("Contraseña2")
                        .photo("photo.png").build()
        );
        Mockito.when(userService.findAll()).thenReturn(userDTOS);
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("prueba@prueba.com")))
                .andExpect(jsonPath("$[0].username", is("prueba")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].email", is("Soyunaprueba@prueba.com")))
                .andExpect(jsonPath("$[1].username", is("prueba2")));
    }

    @Test
    void testFindById() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .email("prueba@prueba")
                .username("Juan")
                .password("Contraseña1")
                .photo("photo.png").build();

        Mockito.when(userService.findById(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/" + userDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("prueba@prueba")))
                .andExpect(jsonPath("$.username", is("Juan")));
    }

    @Test
    void testCreate() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .email("prueba@prueba")
                .username("Carlos")
                .password("Contraseña1")
                .photo("photo.png").build();
        Mockito.when (userService.create(userDTO)).thenReturn(userDTO);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("prueba@prueba")))
                .andExpect(jsonPath("$.username", is("Carlos")))
                .andExpect(jsonPath("$.password", is("Contraseña1")))
                .andExpect(jsonPath("$.photo", is("photo.png")));
    }

    @Test
    void testUpdate() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .email("prueba@prueba")
                .username("Carlos")
                .password("Contraseña1")
                .photo("photo.png").build();
        Mockito.when (userService.update(1L, userDTO)).thenReturn(userDTO);

        mockMvc.perform(put("/api/users/" + userDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("prueba@prueba")))
                .andExpect(jsonPath("$.username", is("Carlos")))
                .andExpect(jsonPath("$.password", is("Contraseña1")))
                .andExpect(jsonPath("$.photo", is("photo.png")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }
}