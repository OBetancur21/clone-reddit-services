package com.clone.service.categories.services;

import com.clone.service.categories.dtos.SubCategoryDTO;
import com.clone.service.categories.models.entity.SubCategory;
import com.clone.service.categories.repositories.SubCategoryRepository;
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
class SubCategoryServiceImpTest {
    @Mock
    private SubCategoryRepository subCategoryRepository;

    @InjectMocks
    private SubCategoryServiceImp subCategoryServiceImp;

    @Test
    void testFindAll() {
        SubCategory subCategory1 = SubCategory.builder()
                .id(0L)
                .name("Action")
                .enable(true).build();
        SubCategory subCategory2 = SubCategory.builder()
                .id(1L)
                .name("Adventure")
                .enable(true).build();
        List<SubCategory> subCategories = Arrays.asList(subCategory1, subCategory2);
        //When // Se ejecute la funcion
        Mockito.when(subCategoryRepository.findAll()).thenReturn(subCategories);

        List<SubCategoryDTO> subCategoryDTOS = subCategoryServiceImp.findAll();

        //Then// Respondera
        assertEquals(subCategories.size(), subCategoryDTOS.size());
        assertEquals(subCategories.get(0).getId(), subCategoryDTOS.get(0).getId());
    }

    @Test
    void testFindById() {
        SubCategory subCategory = SubCategory.builder()
                .id(0L)
                .name("Action")
                .enable(true).build();
        //When // Se ejecute la funcion
        Mockito.when(subCategoryRepository.findById(0l)).thenReturn(Optional.of(subCategory));

        SubCategoryDTO subCategoryDTO = subCategoryServiceImp.findById(0L);

        //Then// Respondera
        assertNotNull(subCategoryDTO);
        assertEquals(subCategory.getId(), subCategoryDTO.getId());
    }

    @Test
    void testCreate(){
        SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder()
                .id(1L)
                .name("Action").build();
        SubCategory subCategory = SubCategory.builder()
                .id(1L)
                .name("Action").build();
        //When // Se ejecute la funcion
        Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        SubCategoryDTO createsubCategoryDTO = subCategoryServiceImp.create(subCategoryDTO);

        //Then// Respondera
        assertEquals(subCategoryDTO.getId(), createsubCategoryDTO.getId());
        assertEquals(subCategoryDTO.getName(), createsubCategoryDTO.getName());
    }

    @Test
    void testUpdate(){
        SubCategoryDTO subCategoryDTO = SubCategoryDTO.builder()
                .id(1L)
                .name("Action").build();
        SubCategory subCategory = SubCategory.builder()
                .id(1L)
                .name("Action").build();
        //When // Se ejecute la funcion
        Mockito.when(subCategoryRepository.getReferenceById(1L)).thenReturn(subCategory);
        Mockito.when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);
        SubCategoryDTO updatesubCategoryDTO = subCategoryServiceImp.update(1L, subCategoryDTO);

        //Then// Respondera
        assertEquals(subCategoryDTO.getId(), updatesubCategoryDTO.getId());
        assertEquals(subCategoryDTO.getName(), updatesubCategoryDTO.getName());
    }

    @Test
    void testDelete(){
        Mockito.doNothing().when(subCategoryRepository).deleteById(1L); //No devuelve nada
        subCategoryServiceImp.delete(1L);
        Mockito.verify(subCategoryRepository, Mockito.times(1)).deleteById(1L);
    }
}