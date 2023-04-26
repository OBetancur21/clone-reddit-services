package com.clone.service.comments.controllers;
import com.clone.service.comments.dtos.CommentDTO;
import com.clone.service.comments.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentDTO>> findByPostId(@PathVariable("id") String id){
        return ResponseEntity.ok(commentService.findByPostId(id));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CommentDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(commentDTO));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CommentDTO> update(@PathVariable("id") Long id,
                                              @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.update(id, commentDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {commentService.delete(id);}
}
