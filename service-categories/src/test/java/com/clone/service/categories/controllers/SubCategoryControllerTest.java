package com.clone.service.categories.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.categories.dtos.SubCategoryDTO;
import com.clone.service.categories.services.SubCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubCategoryController.class)
class SubCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubCategoryService subCategoryService;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception{
        List<SubCategoryDTO> subCategoryDTOS = Arrays.asList(
                SubCategoryDTO.builder()
                        .id(1L)
                        .name("Sports")
                        .build(),
                SubCategoryDTO.builder()
                        .id(2L)
                        .name("Pranks")
                        .build()
        );
        Mockito.when(subCategoryService.findAll()).thenReturn(subCategoryDTOS);

        mockMvc.perform(get("/api/subcategories"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Sports")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Pranks")));
    }

    @Test
    void testFindById() throws Exception{
        SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .build();
        Mockito.when(subCategoryService.findById(1L)).thenReturn(subCategoryDTO);

        mockMvc.perform(get("/api/subcategories/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sports")));
    }

    @Test
    void testCreate() throws Exception{
        SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .build();
        Mockito.when(subCategoryService.create(subCategoryDTO)).thenReturn(subCategoryDTO);

        mockMvc.perform(post("/api/subcategories")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(subCategoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sports")));
    }

    @Test
    void testUpdate() throws Exception{
        SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder()
                .id(1L)
                .name("Sports")
                .build();
        Mockito.when(subCategoryService.update(1L, subCategoryDTO)).thenReturn(subCategoryDTO);

        mockMvc.perform(put("/api/subcategories/1")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(subCategoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sports")));
    }

    @Test
    void testDelete() throws Exception{
        mockMvc.perform(delete("/api/subcategories/1"))
                .andExpect(status().isOk());
    }
}