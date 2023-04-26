package com.clone.service.categories.services;

import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.models.entity.Category;
import com.clone.service.categories.repositories.CategoryRepository;
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
class CategoryServiceImpTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImp categoryServiceImp;

    @Test
    void testFiandAll(){
        //BDD Given // Los datos entradas
        Category category1 = Category.builder()
                .id(1L)
                .name("VideoGames")
                .enable(true)
                .icon("icon.png").build();

        Category category2 = Category.builder()
                .id(2L)
                .name("Cook")
                .enable(false)
                .icon("icon.png").build();
        List<Category> categories = Arrays.asList(category1, category2);

        //When // Se ejecute la funcion
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryServiceImp.findAll();

        //Then// Respondera
        assertEquals(categories.size(), categoryDTOS.size());
        assertEquals(categories.get(0).getId(), categoryDTOS.get(0).getId());
    }

    @Test
    void  testFindById() {
        //BDD Given // Los datos entradas
        Category category = Category.builder()
                .id(1L)
                .name("VideoGames")
                .enable(true)
                .icon("icon.png").build();
        //When // Se ejecute la funcion
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDTO categoryDTO = categoryServiceImp.findById(1L);

        // Then
        assertNotNull(categoryDTO);
        assertEquals(category.getId(), categoryDTO.getId());
    }
    @Test
    void testCreate(){
        //Given
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category")
                .icon("icon-png").build();
        Category category = Category.builder()
                .id(1L)
                .name("Category")
                .icon("icon-png").build();
        //When
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        CategoryDTO createdCategory = categoryServiceImp.create(categoryDTO);
        //Then
        assertEquals(categoryDTO.getId(), createdCategory.getId());
        assertEquals(categoryDTO.getName(), createdCategory.getName());

    }
    @Test
    void testUpdate(){
        //Given
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Categoria 1")
                .icon("icon-png").build();
        Category category = Category.builder()
                .id(1L)
                .name("Category")
                .icon("icon-png").build();
        //When
        Mockito.when(categoryRepository.getReferenceById(1L)).thenReturn(category);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        CategoryDTO categoryDTO1 = categoryServiceImp.update(1L, categoryDTO);
        //Then
        assertNotNull(categoryDTO1);
        assertEquals(categoryDTO.getId(), categoryDTO1.getId());
        assertEquals(categoryDTO.getName(), categoryDTO1.getName());
        assertEquals(categoryDTO.getIcon(), categoryDTO1.getIcon());
    }

    @Test
    void testDelete(){

        //Then
        Mockito.doNothing().when(categoryRepository).deleteById(1L);
        categoryServiceImp.delete(1L);
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(1L);
    }
}