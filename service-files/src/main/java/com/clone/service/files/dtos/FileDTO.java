package com.clone.service.files.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class FileDTO {
    private Long id;
    @NotNull(message = "The file must not be null")
    @NotBlank(message = "The file must not be blank")
    private String name;
    private String postId;
}
