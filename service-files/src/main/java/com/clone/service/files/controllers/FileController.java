package com.clone.service.files.controllers;

import com.clone.service.files.dtos.FileDTO;
import com.clone.service.files.services.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileDTO>> findAll(){
        return ResponseEntity.ok(fileService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<List<FileDTO>> findByPostId(@PathVariable("id") String id) {
        return ResponseEntity.ok(fileService.findByPostId(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(fileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FileDTO> create(@Valid @RequestBody FileDTO fileDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.create(fileDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileDTO> update(@PathVariable("id")Long id,
                                          @RequestBody FileDTO fileDTO){
        return ResponseEntity.ok(fileService.update(id, fileDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {fileService.delete(id);}

}
