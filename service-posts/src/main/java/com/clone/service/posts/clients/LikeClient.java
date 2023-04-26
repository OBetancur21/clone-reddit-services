package com.clone.service.posts.clients;

import com.clone.service.posts.models.Like;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-likes", path = "/api/likes")
public interface LikeClient {
    @GetMapping("/post/{id}")
    List<Like> findByPostId(@PathVariable("id") String id);
}
