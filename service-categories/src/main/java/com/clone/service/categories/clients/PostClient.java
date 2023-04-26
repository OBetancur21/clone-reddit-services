package com.clone.service.categories.clients;

import com.clone.service.categories.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-posts", path = "/api/posts")
public interface PostClient {
    @GetMapping("/sub-categories/{id}")
    List<Post> findBySubCategoryId(@PathVariable("id") Long id);
}