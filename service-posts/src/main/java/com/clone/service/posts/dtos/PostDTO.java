package com.clone.service.posts.dtos;

import com.clone.service.posts.models.Comment;
import com.clone.service.posts.models.Favorite;
import com.clone.service.posts.models.File;
import com.clone.service.posts.models.Like;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class PostDTO {

    private String id;
    @NotNull
    @NotBlank(message = "The title is obligatory")
    private String title;

    @NotNull
    @NotBlank(message = "The description cannot be empty")
    private String description;
    private Long subCategoryId;
    private Long userId;
    private List<File> files;
    private List<Comment> comments;
    private List<Favorite> favorites;
    private List<Like> likes;
}
