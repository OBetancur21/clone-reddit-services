package com.clone.service.favorites.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.favorites.dtos.FavoriteDTO;
import com.clone.service.favorites.services.FavoriteService;
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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(FavoriteController.class)

//se debe terminar los test cuando se encuentre enlazado con el microservice de Posts
class FavoriteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FavoriteService favoriteService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception {
        List<FavoriteDTO> favoriteDTOS = Arrays.asList(
                FavoriteDTO.builder()
                        .id(1L)
                        .build(),
                FavoriteDTO.builder()
                        .id(2L)
                        .build()
        );
        mockMvc.perform(get("/api/favorites"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .id(1L)
                .build();
        mockMvc.perform(get("/api/favorites/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreated() throws Exception {
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .id(1L)
                .build();
        Mockito.when(favoriteService.create(favoriteDTO)).thenReturn(favoriteDTO);

        mockMvc.perform(post("/api/favorites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(favoriteDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .id(1L)
                .build();
        Mockito.when(favoriteService.update(1L, favoriteDTO)).thenReturn(favoriteDTO);

        mockMvc.perform(put("/api/favorites/" + favoriteDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(favoriteDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/favorites/1"))
                .andExpect(status().isNoContent());
    }
}