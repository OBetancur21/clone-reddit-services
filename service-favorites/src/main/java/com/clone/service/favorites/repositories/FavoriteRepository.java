package com.clone.service.favorites.repositories;

import com.clone.service.favorites.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f WHERE f.postId = ?1")
    List<Favorite> findByPostId(String postId);
}
