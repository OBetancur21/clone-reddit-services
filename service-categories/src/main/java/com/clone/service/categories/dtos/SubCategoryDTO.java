package com.clone.service.categories.dtos;

import com.clone.service.categories.models.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class SubCategoryDTO {
    private Long id;
    @NotBlank(message = "Name this SubCategory is required")
    private  String name;
    private CategoryDTO categoryDTO;
    private List<Post> posts;
}
