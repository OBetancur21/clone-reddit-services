package com.clone.service.likes.controllers;

import com.clone.service.likes.dtos.LikeDTO;
import com.clone.service.likes.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @GetMapping
    public ResponseEntity<List<LikeDTO>> findAll(){
        return ResponseEntity.ok(likeService.findAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<LikeDTO>> findByPostId(@PathVariable("id") String id){
        return ResponseEntity.ok(likeService.findByPostId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(likeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LikeDTO> create(@RequestBody LikeDTO likeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.create(likeDTO));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<LikeDTO> update(@PathVariable("id")Long id,
                                           @RequestBody LikeDTO likeDTO){
        return ResponseEntity.ok(likeService.update(id,likeDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {likeService.delete(id);}
}


