package com.clone.service.files.repositories;

import com.clone.service.files.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    @Query("SELECT f FROM File f WHERE f.postId = ?1")
    List<File> findByPostId(String postId);
}