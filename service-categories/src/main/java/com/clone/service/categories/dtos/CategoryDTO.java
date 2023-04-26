package com.clone.service.categories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private  String icon;
}
