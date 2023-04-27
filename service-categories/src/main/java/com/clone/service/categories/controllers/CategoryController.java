package com.clone.service.categories.controllers;

import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){return ResponseEntity.ok(categoryService.findAll());
    }

    //Parametro de ruta
    @GetMapping("/{id}")
    public  ResponseEntity<CategoryDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    //ResponseEntity para proveer una peticionn completa(Body, header, status)
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Long id,
                                              @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
}
