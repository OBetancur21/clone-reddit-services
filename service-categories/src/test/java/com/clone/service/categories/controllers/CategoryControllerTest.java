package com.clone.service.categories.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();}

    @Test
    void testFindAll() throws Exception {
        List<CategoryDTO> categoryDTOS = Arrays.asList(
                CategoryDTO.builder()
                            .id(1L)
                            .name("VideoGames")
                            .icon("icon.png")
                            .build(),
                CategoryDTO.builder()
                            .id(2L)
                            .name("Cook")
                            .icon("icon.png")
                            .build()
        );

        Mockito.when(categoryService.findAll()).thenReturn(categoryDTOS);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("VideoGames")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Cook")));
    }

    @Test
    void testFindById() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("VideoGames")
                .icon("icon.png")
                .build();

        Mockito.when(categoryService.findById(1L)).thenReturn(categoryDTO);

        //Then
        mockMvc.perform(get("/api/categories/" + categoryDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("VideoGames")));

    }

    @Test
    void testCreate() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("VideoGames")
                .icon("icon.png")
                .build();

        Mockito.when(categoryService.create(categoryDTO)).thenReturn(categoryDTO);


        //Then
        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("VideoGames")));
}

    @Test
    void testUpdate() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("VideoGames")
                .icon("icon.png")
                .build();

        Mockito.when(categoryService.update(1L, categoryDTO)).thenReturn(categoryDTO);

        //Then
        mockMvc.perform(put("/api/categories/" + categoryDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(categoryDTO))) // convert object to json
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("VideoGames")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isOk());
    }
}