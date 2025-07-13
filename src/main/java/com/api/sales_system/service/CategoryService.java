package com.api.sales_system.service;

import com.api.sales_system.dto.CategoryCreateDTO;
import com.api.sales_system.dto.CategoryResponseDTO;
import com.api.sales_system.dto.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);
    void deleteCategoryById(Long id);
    CategoryResponseDTO getCategoryById(Long id);
    CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO);
    List<CategoryResponseDTO> getCategories();
}
