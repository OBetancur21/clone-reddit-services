package com.clone.service.comments.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class CommentDTO {
    private Long id;

    @Size(min = 1, max = 300 , message = "Comment must be between 1 and 300 characters")
    @Size(max = 300, message = "The text must not exceed 300 characters")
    private String comment;
    private String postId;
}
