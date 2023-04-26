package com.clone.service.likes.repositories;

import com.clone.service.likes.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
@Query("SELECT l FROM Like l WHERE l.postId = ?1")
    List<Like> findByPostId(String postId);
}
