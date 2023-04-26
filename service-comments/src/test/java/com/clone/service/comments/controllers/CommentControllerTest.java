package com.clone.service.comments.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.comments.dtos.CommentDTO;
import com.clone.service.comments.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception {
        List<CommentDTO> commentDTOS = Arrays.asList(
                CommentDTO.builder()
                        .id(1L)
                        .comment("Best Comment Ever")
                        .build(),
                CommentDTO.builder()
                        .id(2L)
                        .comment("Second Best Comment Ever")
                        .build()
        );

        Mockito.when(commentService.findAll()).thenReturn(commentDTOS);

        mockMvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].comment", is("Best Comment Ever")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].comment", is("Second Best Comment Ever"))
                );
    }

    @Test
    void testFindById() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Best Comment Ever")
                .build();

        Mockito.when(commentService.findById(1L)).thenReturn(commentDTO);

        mockMvc.perform(get("/api/comments/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.comment", is("Best Comment Ever")));
    }

    @Test
    void testCreate() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Best Comment Ever")
                .build();

        Mockito.when(commentService.create(commentDTO)).thenReturn(commentDTO);

        mockMvc.perform(post("/api/comments")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.comment", is("Best Comment Ever")));
    }

    @Test
    void testUpdate() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Best Comment Ever")
                .build();

        Mockito.when(commentService.update(1L, commentDTO)).thenReturn(commentDTO);

        mockMvc.perform(put("/api/comments/1")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.comment", is("Best Comment Ever")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/comments/1")).andExpect(status().isOk());
    }

}