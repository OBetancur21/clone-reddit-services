package com.clone.service.favorites.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class FavoriteDTO {
    private Long id;
    private String postId;
}
