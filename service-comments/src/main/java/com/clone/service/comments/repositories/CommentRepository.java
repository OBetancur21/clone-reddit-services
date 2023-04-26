package com.clone.service.comments.repositories;

import com.clone.service.comments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.postId = ?1")
    List<Comment> findByPostId(String postId);
}
