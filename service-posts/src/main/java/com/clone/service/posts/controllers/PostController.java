package com.clone.service.posts.controllers;

import com.clone.service.posts.dtos.PostDTO;
import com.clone.service.posts.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/sub-categories/{id}")
    public ResponseEntity<List<PostDTO>> findBySubCategoryId(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.findBySubCategoryId(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<PostDTO>> findByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.findByUserId(id));
    }
//  El posts no va cambiar de ruta  solo va a a traer lo que nosotros le digamos en este caso se implemento uun files
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable("id")String id,
                                          @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.update(id,postDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {postService.delete("id");}

}
