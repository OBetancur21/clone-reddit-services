package com.clone.service.likes.services;

import com.clone.service.likes.dtos.LikeDTO;
import com.clone.service.likes.models.Like;
import com.clone.service.likes.repositories.LikeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImp implements LikeService{
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<LikeDTO> findAll() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LikeDTO> findByPostId(String postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        return likes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LikeDTO findById(Long id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Like not found with id " + id));
        return convertToDTO(like);
    }

    @Override
    public LikeDTO create(LikeDTO likeDTO) {
        Like like = convertToEntity(likeDTO);
        Like saveLike = likeRepository.save(like);
        return convertToDTO(saveLike);
    }

    @Override
    public LikeDTO update(Long id, LikeDTO likeDTO){
        Like like = likeRepository.getReferenceById(id);
        like.setNumber_like(likeDTO.getNumber_like());
        Like updateLike = likeRepository.save(like);
        return convertToDTO(updateLike);
    }

    @Override
    public void delete(Long id){likeRepository.deleteById(id);}

    private LikeDTO convertToDTO(Like like){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setNumber_like(like.getNumber_like());
        return likeDTO;
    }
    private Like convertToEntity(LikeDTO likeDTO){
        Like like = new Like();
        like.setId(like.getId());
        like.setNumber_like(likeDTO.getNumber_like());
        return like;
    }
}
