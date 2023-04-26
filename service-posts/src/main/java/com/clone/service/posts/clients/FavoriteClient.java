package com.clone.service.posts.clients;

import com.clone.service.posts.models.Favorite;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-favorites", path = "/api/favorites")
public interface FavoriteClient {
    @GetMapping("/post/{id}")
    List<Favorite> findByPostId(@PathVariable("id") String id);
}
