package com.clone.service.categories.services;

import com.clone.service.categories.clients.PostClient;
import com.clone.service.categories.dtos.CategoryDTO;
import com.clone.service.categories.dtos.SubCategoryDTO;
import com.clone.service.categories.models.Post;
import com.clone.service.categories.models.entity.Category;
import com.clone.service.categories.models.entity.SubCategory;
import com.clone.service.categories.repositories.SubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImp implements SubCategoryService{
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private PostClient postClient;

    @Override
    public List<SubCategoryDTO> findAll() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return subCategories.stream()
                //.map(subCategory -> modelMapper.map(subCategory, SubCategoryDTO.class))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubCategoryDTO findBySubCategoryId(Long id) {
        SubCategory subCategory = subCategoryRepository.getReferenceById(id);
        if (subCategory != null){
            List<Post> posts = postClient.findBySubCategoryId(id);
            subCategory.setPosts(posts);
        }
        return convertToDTOWithPosts(subCategory);
    }

    @Override
    public SubCategoryDTO findById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategorie not found with by id " + id));
        return convertToDTO(subCategory);
    }

    @Override
    public SubCategoryDTO create(SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = convertToEntity(subCategoryDTO);
        SubCategory saveSubCategory = subCategoryRepository.save(subCategory);
        return convertToDTO(saveSubCategory);
    }

   @Override
   public SubCategoryDTO update(Long id, SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = subCategoryRepository.getReferenceById(id);
        subCategory.setName(subCategoryDTO.getName());
        SubCategory updateSubCategory = subCategoryRepository.save(subCategory);
        return convertToDTO(updateSubCategory);
   }

    @Override
    public void delete(Long id) {
        subCategoryRepository.deleteById(id);
    }

    public SubCategoryDTO convertToDTO(SubCategory subCategory){
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setId(subCategory.getId());
        subCategoryDTO.setName(subCategory.getName());
        subCategoryDTO.setCategoryDTO(convertCategoryDTO(subCategory.getCategory()));
        return subCategoryDTO;
    }

    public SubCategoryDTO convertToDTOWithPosts( SubCategory subCategory){
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setId(subCategory.getId());
        subCategoryDTO.setName(subCategory.getName());
        subCategoryDTO.setPosts(subCategory.getPosts());
        return subCategoryDTO;
    }

    public CategoryDTO convertCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setIcon(category.getIcon());
        return categoryDTO;
    }

    public SubCategory convertToEntity(SubCategoryDTO subCategoryDTO){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(subCategoryDTO.getId());
        subCategory.setName(subCategoryDTO.getName());
        return  subCategory;
    }
}
