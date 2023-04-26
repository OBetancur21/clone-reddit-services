package com.clone.service.favorites.controllers;

import com.clone.service.favorites.dtos.FavoriteDTO;
import com.clone.service.favorites.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteDTO>> findAll(){
        return  ResponseEntity.ok(favoriteService.findAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<FavoriteDTO>> findByPostId(@PathVariable("id") String postId){
        return ResponseEntity.ok(favoriteService.findByPostId(postId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(favoriteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FavoriteDTO> create(@RequestBody FavoriteDTO favoriteDTO){
        return ResponseEntity.ok(favoriteService.create(favoriteDTO));
    }

    @PutMapping
    public  ResponseEntity<FavoriteDTO> update(@PathVariable("id") Long id,
                                               @RequestBody FavoriteDTO favoriteDTO){
        return ResponseEntity.ok(favoriteService.update(id, favoriteDTO));
    }

  @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {favoriteService.delete(id); }
}
