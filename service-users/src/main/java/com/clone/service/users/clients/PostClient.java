package com.clone.service.users.clients;

import com.clone.service.users.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-posts", path = "/api/posts")
public interface PostClient {
    @GetMapping("/users/{id}")
    List<Post> findByUserId(@PathVariable("id") Long id);
}
