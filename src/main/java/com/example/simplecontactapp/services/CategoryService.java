package com.example.simplecontactapp.services;

import com.example.simplecontactapp.Dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto category);
    void delete(Long id);
    List<CategoryDto> findCategoryByUserId(Long userId);
    CategoryDto findById(Long id);
    List<CategoryDto> getAllTodoByCategoriesForToday(Long userId);
}
