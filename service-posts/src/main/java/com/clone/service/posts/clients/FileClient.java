package com.clone.service.posts.clients;

import com.clone.service.posts.models.File;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-files", path = "/api/files")
public interface FileClient {
    @GetMapping("/posts/{id}")
    List<File> findByPostId(@PathVariable("id") String id);
}
