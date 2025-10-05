package com.example.bookstore.service;

import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

  
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        Category saved = categoryRepository.save(category);
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(saved, dto);
        return dto;
    }

   
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(cat -> {
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(cat, dto);
            return dto;
        }).collect(Collectors.toList());
    }

   
    public CategoryDto getCategoryById(Long id) {
        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(cat, dto);
        return dto;
    }

    
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(categoryDto.getName());
            Category updated = categoryRepository.save(existing);
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(updated, dto);
            return dto;
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

   
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
