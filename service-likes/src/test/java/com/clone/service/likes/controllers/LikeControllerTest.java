package com.clone.service.likes.controllers;

import com.clone.service.likes.dtos.LikeDTO;
import com.clone.service.likes.services.LikeService;
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
@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeService likeService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception{
        List<LikeDTO> likeDTOS = Arrays.asList(
                LikeDTO.builder()
                        .id(1L)
                        .number_like(1)
                        .build(),
                LikeDTO.builder()
                        .id(2L)
                        .number_like(2)
                        .build()
        );

        Mockito.when (likeService.findAll()).thenReturn(likeDTOS);

        mockMvc.perform(get("/api/likes"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].number_like", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].number_like", is(2)));
    }

    @Test
    void testFindById() throws Exception{
        LikeDTO likeDTO = LikeDTO.builder()
                .id(1L)
                .number_like(1)
                .build();

        Mockito.when (likeService.findById(1L)).thenReturn(likeDTO);

        mockMvc.perform(get("/api/likes/" + likeDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number_like", is(1)));
    }

    @Test
    void testCreate() throws Exception{
        LikeDTO likeDTO = LikeDTO.builder()
                .id(1L)
                .number_like(1)
                .build();

        Mockito.when (likeService.create(likeDTO)).thenReturn(likeDTO);

        mockMvc.perform(post("/api/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(likeDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number_like", is(1)));
    }

    @Test
    void testUpdate() throws Exception{
        LikeDTO likeDTO = LikeDTO.builder()
                .id(1L)
                .number_like(1)
                .build();

        Mockito.when (likeService.update(1L, likeDTO)).thenReturn(likeDTO);

        mockMvc.perform(put("/api/likes/" + likeDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(likeDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.number_like", is(1)));
    }

    @Test
    void testDelete() throws Exception{
   mockMvc.perform(delete("/api/likes/1"))
                .andExpect(status().isOk());
    }
}