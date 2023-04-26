package com.clone.service.posts.services;

import com.clone.service.posts.dtos.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> findAll();
    List<PostDTO> findBySubCategoryId(Long subCategoryId);
    List<PostDTO> findByUserId(Long userId);
    PostDTO findById(String id);
    PostDTO create(PostDTO postDTO);
    PostDTO update(String id, PostDTO postDTO);
    void delete(String id);
}
