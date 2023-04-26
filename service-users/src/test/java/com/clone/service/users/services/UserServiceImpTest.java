package com.clone.service.users.services;

import com.clone.service.users.dtos.UserDTO;
import com.clone.service.users.models.entity.User;
import com.clone.service.users.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Test
    void testFindAll() {
        User user1 = User.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();
        User user2 = User.builder()
                .id(2L)
                .email("nuevo2")
                .username("nuevo2")
                .password("nuevo2")
                .photo("nuevo2").build();
        List<User> users = Arrays.asList(user1, user2);

        Mockito.when (userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOS = userServiceImp.findAll();

        assertEquals(users.size(), userDTOS.size());
        assertEquals(users.get(0).getId(), userDTOS.get(0).getId());
    }

    @Test
    void testFindById () {
        User user = User.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();

        Mockito.when (userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = userServiceImp.findById(1L);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
    }

    @Test
    void testCreate() {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();
        User user = User.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();

        Mockito.when (userRepository.save(Mockito.any(User.class))).thenReturn(user);
        UserDTO createdUser = userServiceImp.create(userDTO);

        assertEquals(userDTO.getId(), createdUser.getId());
        assertEquals(userDTO.getEmail(), createdUser.getEmail());
        assertEquals(userDTO.getUsername(), createdUser.getUsername());
        assertEquals(userDTO.getPassword(), createdUser.getPassword());
        assertEquals(userDTO.getPhoto(), createdUser.getPhoto());
    }

    @Test
    void testUpdate(){
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();
        User user = User.builder()
                .id(1L)
                .email("nuevo")
                .username("nuevo")
                .password("nuevo")
                .photo("nuevo").build();
        Mockito.when(userRepository.getReferenceById(1L)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserDTO updatedUser = userServiceImp.update(1L, userDTO);

        assertNotNull(updatedUser);
        assertEquals(userDTO.getId(), updatedUser.getId());
        assertEquals(userDTO.getEmail(), updatedUser.getEmail());
        assertEquals(userDTO.getUsername(), updatedUser.getUsername());
        assertEquals(userDTO.getPassword(), updatedUser.getPassword());
        assertEquals(userDTO.getPhoto(), updatedUser.getPhoto());
    }

    @Test
    void testDelete() {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        userServiceImp.delete(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }
}