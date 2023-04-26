package com.clone.service.comments.services;

import com.clone.service.comments.dtos.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    List<CommentDTO> findByPostId(String postId);
    CommentDTO findById(Long id);

    CommentDTO create(CommentDTO commentDTO);

    CommentDTO update(Long id, CommentDTO commentDTO);

    void delete(Long id);
}
