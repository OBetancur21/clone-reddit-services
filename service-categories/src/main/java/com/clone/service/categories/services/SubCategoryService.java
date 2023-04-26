package com.clone.service.categories.services;

import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.dtos.SubCategoryDTO;
import com.clone.service.categories.models.Post;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryDTO> findAll();

    SubCategoryDTO findBySubCategoryId(Long id);
    SubCategoryDTO findById(Long id);

    SubCategoryDTO create(SubCategoryDTO subCategoryDTO);

    SubCategoryDTO update(Long id, SubCategoryDTO subCategoryDTO);

    void delete(Long id);
}
