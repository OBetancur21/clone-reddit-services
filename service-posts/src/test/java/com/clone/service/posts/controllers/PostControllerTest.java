package com.clone.service.posts.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.posts.services.PostService;
import com.clone.service.posts.dtos.PostDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception {
        List<PostDTO> postDTOS = Arrays.asList(
                PostDTO.builder()
                        .id("1L")
                        .title("VideoGames")
                        .description("Algun comentario")
                        .build(),
                PostDTO.builder()
                        .id("2L")
                        .title("Cook")
                        .description("soy una descripcion")
                        .build()
        );

        Mockito.when(postService.findAll()).thenReturn(postDTOS);

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1L")))
                .andExpect(jsonPath("$[0].title", is("VideoGames")))
                .andExpect(jsonPath("$[1].id", is("2L")))
                .andExpect(jsonPath("$[1].title", is("Cook")));

    }

    @Test
    void testFindById() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .id("1L")
                .title("VideoGames")
                .description("Algun comentario")
                .build();

        Mockito.when(postService.findById("1L")).thenReturn(postDTO);

        mockMvc.perform(get("/api/posts/" + postDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("1L")))
                .andExpect(jsonPath("$.title", is("VideoGames")))
                .andExpect(jsonPath("$.description", is("Algun comentario")));
    }

    @Test
    void testCreate() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .id("1L")
                .title("VideoGames")
                .description("Algun comentario")
                .build();

        Mockito.when(postService.create(postDTO)).thenReturn(postDTO);

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("1L")))
                .andExpect(jsonPath("$.title", is("VideoGames")))
                .andExpect(jsonPath("$.description", is("Algun comentario")));
    }

    @Test
    void testUpdate() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .id("1L")
                .title("VideoGames")
                .description("Algun comentario")
                .build();

        Mockito.when(postService.update("1L", postDTO)).thenReturn(postDTO);

        mockMvc.perform(put("/api/posts/"+ postDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("1L")))
                .andExpect(jsonPath("$.title", is("VideoGames")))
                .andExpect(jsonPath("$.description", is("Algun comentario")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
    }
}