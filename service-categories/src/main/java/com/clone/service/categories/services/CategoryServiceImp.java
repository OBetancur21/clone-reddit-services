package com.clone.service.categories.services;

import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.models.entity.Category;
import com.clone.service.categories.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        return convertToDTO(category);
    }

    //modificador de acceso(public, private, protected)tipo de retorno, name (parametros)
    @Override
    // modifi  retorno  nombre del metodo y parametros
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = converToEntity(categoryDTO);
        Category saveCategory = categoryRepository.save(category);
        return convertToDTO(saveCategory);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.getReferenceById(id);
        category.setName(categoryDTO.getName());
        category.setIcon(categoryDTO.getIcon());
        Category updateCategory = categoryRepository.save(category);
        return convertToDTO(updateCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO convertToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setIcon(category.getIcon());
        return categoryDTO;
    }

    private Category converToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setIcon(categoryDTO.getIcon());
        return category;
    }

}
