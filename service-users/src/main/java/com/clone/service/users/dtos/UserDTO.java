package com.clone.service.users.dtos;

import com.clone.service.users.models.Post;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class UserDTO {

    private Long id;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Email is required")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*).+$", message = "Password must have at least one uppercase letter and one number")
    private String password;
    private String photo;
    private List<Post> posts;
}
