package com.clone.service.likes.services;

import com.clone.service.likes.dtos.LikeDTO;
import com.clone.service.likes.models.Like;
import com.clone.service.likes.repositories.LikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class LikeServiceImpTest {
    @Mock
    private LikeRepository likeRepository;
    @InjectMocks
    private LikeServiceImp likeServiceImp;
    @Test
    void testFindAll() {
        //BDD Given // Los datos entradas
        Like like1 = Like.builder()
                .id(1L)
                .number_like(1)
                .build();
        Like like2 = Like.builder()
                .id(2L)
                .number_like(2)
                .build();
        List<Like> likes = Arrays.asList(like1, like2);

        //When // Se ejecute la funcion
        Mockito.when(likeRepository.findAll()).thenReturn(likes);

        List<LikeDTO> likeDTOS = likeServiceImp.findAll();

        //Then// Respondera
        assertEquals(likes.size(), likeDTOS.size());
        assertEquals(likes.get(0).getId(), likeDTOS.get(0).getId());
    }

    @Test
    void testFindById() {
        //BDD Given // Los datos entradas
        Like like = Like.builder()
                .id(1L)
                .number_like(1)
                .build();

        //When // Se ejecute la funcion
        Mockito.when(likeRepository.findById(1L)).thenReturn(java.util.Optional.of(like));

        LikeDTO likeDTO = likeServiceImp.findById(1L);

        //Then// Respondera
        assertEquals(like.getId(), likeDTO.getId());
        assertEquals(like.getNumber_like(), likeDTO.getNumber_like());
    }

    @Test
    void testCreate() {
        //BDD Given // Los datos entradas
        LikeDTO likeDTO = LikeDTO.builder()

                .number_like(1)
                .build();
        Like like = Like.builder()

                .number_like(1)
                .build();

        //When // Se ejecute la funcion
        Mockito.when(likeRepository.save(like)).thenReturn(like);
        LikeDTO createdLike = likeServiceImp.create(likeDTO);

        //Then// Respondera
        assertEquals(likeDTO.getNumber_like(), createdLike.getNumber_like());
    }

@Test
    void testUpdate() {
        //BDD Given // Los datos entradas
        LikeDTO likeDTO = LikeDTO.builder()
                .id(1L)
                .number_like(1)
                .build();
        Like like = Like.builder()
                .id(1L)
                .number_like(1)
                .build();

        //When // Se ejecute la funcion
        Mockito.when(likeRepository.getReferenceById(1L)).thenReturn(like);
        Mockito.when(likeRepository.save(like)).thenReturn(like);
        LikeDTO updatedLike = likeServiceImp.update(1L,likeDTO);

        //Then// Respondera
        assertEquals(likeDTO.getNumber_like(), updatedLike.getNumber_like());
    }

    @Test
    void testDelete() {
        Mockito.doNothing().when(likeRepository).deleteById(1L);
        likeServiceImp.delete(1L);
        Mockito.verify(likeRepository, Mockito.times(1)).deleteById(1L);
    }

}