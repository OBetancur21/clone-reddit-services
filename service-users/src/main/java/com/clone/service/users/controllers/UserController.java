package com.clone.service.users.controllers;

import com.clone.service.users.dtos.UserDTO;
import com.clone.service.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDTO>>findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<UserDTO> findPostsByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id,
                                          @RequestBody UserDTO userDTO) {
        return  ResponseEntity.ok(userService.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
}
