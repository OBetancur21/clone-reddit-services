package com.clone.service.comments.services;

import com.clone.service.comments.dtos.CommentDTO;
import com.clone.service.comments.models.Comment;
import com.clone.service.comments.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<CommentDTO> findAll() {
        List<Comment>comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + id));
        return convertToDTO(comment);
    }

    @Override
    public  CommentDTO create(CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        Comment saveComment = commentRepository.save(comment);
        return convertToDTO(saveComment);
    }

    @Override
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.getReferenceById(id);
        comment.setComment(commentDTO.getComment());
        Comment updateComment = commentRepository.save(comment);
        return convertToDTO(updateComment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentDTO convertToDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setComment(comment.getComment());
        commentDTO.setPostId(comment.getPostId());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());
        comment.setPostId(commentDTO.getPostId());
        return comment;
    }
}

