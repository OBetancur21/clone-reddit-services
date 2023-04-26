package com.clone.service.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Post {
    @Id
    private String id;
    private String title;
    private String description;
}
