package com.clone.service.categories.controllers;

import com.clone.service.categories.dtos.SubCategoryDTO;
import com.clone.service.categories.services.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> findAll(){
        return  ResponseEntity.ok(subCategoryService.findAll());
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<SubCategoryDTO> findPostsBySubCategoryId(@PathVariable("id")Long id){
        return ResponseEntity.ok(subCategoryService.findBySubCategoryId(id));
    }

   @GetMapping("/{id}")
   public ResponseEntity<SubCategoryDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(subCategoryService.findById(id));
   }

    @PostMapping
    public ResponseEntity<SubCategoryDTO>create( @Valid @RequestBody SubCategoryDTO subCategoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.create(subCategoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> update(@PathVariable("id") Long id,
                                                 @RequestBody SubCategoryDTO subCategoryDTO) {
        return ResponseEntity.ok(subCategoryService.update(id, subCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {subCategoryService.delete(id);
    }

    public SubCategoryService getSubCategoryService() {
        return subCategoryService;
    }
}
