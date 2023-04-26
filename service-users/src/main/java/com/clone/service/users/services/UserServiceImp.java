package com.clone.service.users.services;

import com.clone.service.users.clients.PostClient;
import com.clone.service.users.dtos.UserDTO;
import com.clone.service.users.models.Post;
import com.clone.service.users.models.entity.User;
import com.clone.service.users.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostClient postClient;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserId(Long id) {
        User user = userRepository.getReferenceById(id);
        if (user != null){
            List<Post> posts = postClient.findByUserId(id);
            user.setPosts(posts);
        }
        return convertToDTOWithPosts(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        return convertToDTO(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO){
        User user = convertToEntity(userDTO);
        User saveUser = userRepository.save(user);
        return convertToDTO(saveUser);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.getReferenceById(id);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoto(userDTO.getPhoto());
        User updateUser = userRepository.save(user);
        return convertToDTO(updateUser);
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoto(user.getPhoto());
        return userDTO;
    }

    private UserDTO convertToDTOWithPosts(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoto(user.getPhoto());
        userDTO.setPosts(user.getPosts());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoto(userDTO.getPhoto());
        return user;
    }
}
