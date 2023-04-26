package com.clone.service.favorites.services;

import com.clone.service.favorites.dtos.FavoriteDTO;
import com.clone.service.favorites.models.Favorite;
import com.clone.service.favorites.repositories.FavoriteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImp implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FavoriteDTO> findAll() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<FavoriteDTO> findByPostId(String postId) {
        List<Favorite> favorites = favoriteRepository.findByPostId(postId);
        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FavoriteDTO findById(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Favorite not found with id " + id));
        return convertToDTO(favorite);
    }

    @Override
    public FavoriteDTO create(FavoriteDTO favoriteDTO) {
        Favorite favorite = convertToEntity(favoriteDTO);
        Favorite saveFavorite = favoriteRepository.save(favorite);
        return convertToDTO(saveFavorite);
    }

    @Override
    public FavoriteDTO update(Long id, FavoriteDTO favoriteDTO){
        Favorite favorite = favoriteRepository.getReferenceById(id);
        favorite.setId(favoriteDTO.getId());
        Favorite updateFavorite = favoriteRepository.save(favorite);
        return convertToDTO(updateFavorite);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }

    public FavoriteDTO convertToDTO(Favorite favorite){
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setPostId(favorite.getPostId());
        return favoriteDTO;
    }

    public Favorite convertToEntity(FavoriteDTO favoriteDTO){
        Favorite favorite = new Favorite();
        favorite.setId(favorite.getId());
        favorite.setPostId(favorite.getPostId());
        return favorite;
    }
}
