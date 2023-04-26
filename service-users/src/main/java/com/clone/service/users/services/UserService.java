package com.clone.service.users.services;

import com.clone.service.users.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findByUserId(Long id);
    UserDTO findById(Long id);

    UserDTO create(UserDTO userDTO);

    UserDTO update(Long id, UserDTO userDTO);

    void delete(Long id);
}
