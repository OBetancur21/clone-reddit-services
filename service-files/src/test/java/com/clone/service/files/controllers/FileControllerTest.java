package com.clone.service.files.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clone.service.files.dtos.FileDTO;
import com.clone.service.files.services.FileService;
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
@WebMvcTest(FileController.class)
class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception {
        List<FileDTO> fileDTOS = Arrays.asList(
                FileDTO.builder()
                        .id(1L)
                        .name("file.png").build(),
                FileDTO.builder()
                        .id(2L)
                        .name("file.png")
                        .build()
        );
        Mockito.when(fileService.findAll()).thenReturn(fileDTOS);

        mockMvc.perform(get("/api/files"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("file.png")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("file.png")));
    }

    @Test
    void testCreate() throws Exception {
        FileDTO fileDTO = FileDTO.builder()
                .id(1L)
                .name("file.png")
                .build();
        Mockito.when(fileService.create(fileDTO)).thenReturn(fileDTO);

        mockMvc.perform(post("/api/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(fileDTO)))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("file.png")));
    }

    @Test
    void testUpdate() throws Exception {
        FileDTO fileDTO = FileDTO.builder()
                .id(1L)
                .name("file.png")
                .build();
        Mockito.when(fileService.update(1L, fileDTO)).thenReturn(fileDTO);

        mockMvc.perform(put("/api/files/" + fileDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(fileDTO)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("file.png")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/files/1"))
                .andExpect(status().isOk());
    }
}