package com.clone.service.posts.clients;

import com.clone.service.posts.models.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-comments", path = "/api/comments")
public interface CommentClient {
    @GetMapping("/post/{id}")
    List<Comment> findByPostId(@PathVariable("id") String id);
}
