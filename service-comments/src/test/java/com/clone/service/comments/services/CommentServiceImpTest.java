package com.clone.service.comments.services;

import com.clone.service.comments.dtos.CommentDTO;
import com.clone.service.comments.models.Comment;
import com.clone.service.comments.repositories.CommentRepository;
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
class CommentServiceImpTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Test
    void testFindAll() throws Exception{
        //BDD Given // Los datos entradas
        Comment comment = Comment.builder()
                .id(1L)
                .comment("Comment")
                .build();

        Comment comment2 = Comment.builder()
                .id(2L)
                .comment("Comment2")
                .build();

        List<Comment> comments = Arrays.asList(comment, comment2);
        //When // Se ejecute la funcion

        Mockito.when(commentRepository.findAll()).thenReturn(comments);

        List<CommentDTO> commentDTOS = commentServiceImp.findAll();

        //Then// Respondera
        assertEquals(comments.size(), commentDTOS.size());
        assertEquals(comments.get(0).getId(), commentDTOS.get(0).getId());
    }

    @Test
    void testFindById() throws Exception{
        //BDD Given // Los datos entradas
        Comment comment = Comment.builder()
                .id(1L)
                .comment("Comment")
                .build();

        Mockito.when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(comment));

        CommentDTO commentDTO = commentServiceImp.findById(1L);

        assertEquals(comment.getId(), commentDTO.getId());
    }

    @Test
    void testCreate() throws Exception{
        //BDD Given // Los datos entradas
        Comment comment = Comment.builder()
                .id(1L)
                .comment("Comment")
                .build();

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Comment")
                .build();

        Mockito.when(commentRepository.save(comment)).thenReturn(comment);

        CommentDTO commentDTO1 = commentServiceImp.create(commentDTO);

        assertEquals(comment.getId(), commentDTO1.getId());
    }

    @Test
    void testUpdate() throws Exception{
        //BDD Given // Los datos entradas
        Comment comment = Comment.builder()
                .id(1L)
                .comment("Comment")
                .build();

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .comment("Comment")
                .build();

        Mockito.when(commentRepository.getReferenceById(1L)).thenReturn(comment);
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);

        CommentDTO commentDTO1 = commentServiceImp.update(1L, commentDTO);

        assertNotNull(commentDTO1);
        assertEquals(comment.getId(), commentDTO1.getId());
        assertEquals(comment.getComment(), commentDTO1.getComment());
    }

    @Test
    void testDelete() throws Exception{
        Mockito.doNothing().when(commentRepository).deleteById(1L);
        commentRepository.deleteById(1L);
        Mockito.verify(commentRepository, Mockito.times(1)).deleteById(1L);
    }
}