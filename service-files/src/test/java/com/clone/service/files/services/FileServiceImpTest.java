package com.clone.service.files.services;

import com.clone.service.files.dtos.FileDTO;
import com.clone.service.files.models.File;
import com.clone.service.files.repositories.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class FileServiceImpTest {
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileServiceImp fileServiceImp;

    @Test
    void testFindAll(){
        File file1 = File.builder()
                .id(1L)
                .name("file1.png")
                .build();

        File file2 = File.builder()
                .id(2L)
                .name("file2.png")
                .build();
        List<File> files = Arrays.asList(file1, file2);

        Mockito.when (fileRepository.findAll()).thenReturn(files);

        List<FileDTO> fileDTOS = fileServiceImp.findAll();

        assertEquals(files.size(), fileDTOS.size());
        assertEquals(files.get(0).getId(), fileDTOS.get(0).getId());

    }

    @Test
    void testFindById(){
        File file = File.builder()
                .id(1L)
                .name("file1.png")
                .build();

        Mockito.when (fileRepository.findById(1L)).thenReturn(Optional.of(file));

        FileDTO fileDTO = fileServiceImp.findById(1L);

        assertEquals(file.getId(), fileDTO.getId());
        assertEquals(file.getName(), fileDTO.getName());
    }

    @Test
    void testCreate(){
        FileDTO fileDTO = FileDTO.builder()
                .id(1L)
                .name("file1.png")
                .build();
        File file = File.builder()
                .id(1L)
                .name("file1.png")
                .build();

        Mockito.when (fileRepository.save(file)).thenReturn(file);
        FileDTO createdFile = fileServiceImp.create(fileDTO);

        assertEquals(file.getId(), createdFile.getId());
        assertEquals(file.getName(), createdFile.getName());

    }

    @Test
    void testUpdate(){
        FileDTO fileDTO = FileDTO.builder()
                .id(1L)
                .name("file1.png")
                .build();
        File file = File.builder()
                .id(1L)
                .name("file1.png")
                .build();

       Mockito.when(fileRepository.getReferenceById(1L)).thenReturn(file);
       Mockito.when(fileRepository.save(file)).thenReturn(file);
       FileDTO updatedFile = fileServiceImp.update(1L, fileDTO);

        assertEquals(file.getId(), updatedFile.getId());
        assertEquals(file.getName(), updatedFile.getName());
    }

    @Test
    void testDelete(){
        File file = File.builder()
                .id(1L)
                .name("file1.png")
                .build();

       Mockito.doNothing().when(fileRepository).deleteById(1L);
         fileServiceImp.delete(1L);
         Mockito.verify(fileRepository, Mockito.times(1)).deleteById(1L);
    }

}