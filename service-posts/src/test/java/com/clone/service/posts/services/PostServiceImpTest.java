package com.clone.service.posts.services;

import com.clone.service.posts.dtos.PostDTO;
import com.clone.service.posts.models.documents.Post;
import com.clone.service.posts.repositories.PostRepository;
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

class PostServiceImpTest {
@Mock private PostRepository postRepository;

@InjectMocks private PostServiceImp postServiceImp;

@Test
    void testFindAll() {
    Post post1 = Post.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();
    Post post2 = Post.builder()
            .id("2L")
            .title("Post 2")
            .description("Description 2")
            .build();
    List<Post> posts = Arrays.asList(post1, post2);

    Mockito.when(postRepository.findAll()).thenReturn(posts);

    List<PostDTO> postDTOS = postServiceImp.findAll();

    assertEquals(posts.size(), postDTOS.size());
    assertEquals(posts.get(0).getId(), postDTOS.get(0).getId());
    }
    @Test
    void testFindById() {
    Post post = Post.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();

    Mockito.when(postRepository.findById("1L")).thenReturn(Optional.of(post));

    PostDTO postDTO = postServiceImp.findById("1L");

    assertNotNull(postDTO);
    assertEquals(post.getId(), postDTO.getId());
    }

    @Test
    void testCreate() {
    PostDTO postDTO = PostDTO.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();
    Post post = Post.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();

    Mockito.when(postRepository.save(post)).thenReturn(post);
    PostDTO createdPostDTO = postServiceImp.create(postDTO);

    assertEquals(postDTO.getId(), createdPostDTO.getId());
    assertEquals(postDTO.getTitle(), createdPostDTO.getTitle());
    assertEquals(postDTO.getDescription(), createdPostDTO.getDescription());
    }

    @Test
    void testUpdate() {
    PostDTO postDTO = PostDTO.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();
    Post post = Post.builder()
            .id("1L")
            .title("Post 1")
            .description("Description 1")
            .build();
    Mockito.when(postRepository.findById("1L")).thenReturn(Optional.of(post));
    Mockito.when(postRepository.save(post)).thenReturn(post);
    PostDTO updatedPostDTO = postServiceImp.update("1L", postDTO);

    assertNotNull(updatedPostDTO);
    assertEquals(postDTO.getId(), updatedPostDTO.getId());
    assertEquals(postDTO.getTitle(), updatedPostDTO.getTitle());
    assertEquals(postDTO.getDescription(), updatedPostDTO.getDescription());
    }

    @Test
    void testDelete() {
    Mockito.doNothing().when(postRepository).deleteById("1L");
    postServiceImp.delete("1L");
    Mockito.verify(postRepository, Mockito.times(1)).deleteById("1L");
    }
}