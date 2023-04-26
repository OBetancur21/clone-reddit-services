package com.clone.service.categories.repositories;

import com.clone.service.categories.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
