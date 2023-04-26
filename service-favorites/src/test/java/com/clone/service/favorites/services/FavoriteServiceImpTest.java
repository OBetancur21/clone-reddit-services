package com.clone.service.favorites.services;

import com.clone.service.favorites.dtos.FavoriteDTO;
import com.clone.service.favorites.models.Favorite;
import com.clone.service.favorites.repositories.FavoriteRepository;
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
class FavoriteServiceImpTest {
    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteServiceImp favoriteServiceImp;

    @Test
    void testFindAll(){
        Favorite favorite = Favorite.builder()
                .id(1L)
                .build();
        Favorite favorite2 = Favorite.builder()
                .id(2L)
                .build();
        List<Favorite> favorites = Arrays.asList(favorite, favorite2);

        Mockito.when(favoriteRepository.findAll()).thenReturn(favorites);

        List<FavoriteDTO> favoriteDTOS = favoriteServiceImp.findAll();

        assertEquals(favorites.size(), favoriteDTOS.size());
        assertEquals(favorites.get(0).getId(), favoriteDTOS.get(0).getId());
    }

    @Test
    void testFindById(){
        Favorite favorite = Favorite.builder()
                .id(1L)
                .build();

        Mockito.when(favoriteRepository.findById(1L)).thenReturn(Optional.of(favorite));
        FavoriteDTO favoriteDTO = favoriteServiceImp.findById(1L);

        assertNotNull(favoriteDTO);
        assertEquals(favorite.getId(), favoriteDTO.getId());
    }

    @Test
    void testCreate(){ //Falta la relacion Este test no funciona en el momento.
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .id(1L)
                .build();
        Favorite favorite = Favorite.builder()
                .id(1L)
                .build();

        Mockito.when(favoriteRepository.save(favorite)).thenReturn(favorite);
        FavoriteDTO createdFavorite = favoriteServiceImp.create(favoriteDTO);

        assertEquals(favoriteDTO.getId(), createdFavorite.getId());
    }

    @Test
    void testUpdate(){
        FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                .id(1L)
                .build();
        Favorite favorite = Favorite.builder()
                .id(1L)
                .build();

        Mockito.when(favoriteRepository.getReferenceById(1L)).thenReturn(favorite);
        Mockito.when(favoriteRepository.save(favorite)).thenReturn(favorite);
        FavoriteDTO updatedFavorite = favoriteServiceImp.update(1L, favoriteDTO);

        assertEquals(favoriteDTO.getId(), updatedFavorite.getId());
    }

    @Test
    void testDelete(){
        Mockito.doNothing().when(favoriteRepository).deleteById(1L);
        favoriteServiceImp.delete(1L);
        Mockito.verify(favoriteRepository, Mockito.times(1)).deleteById(1L);

    }
}