package com.api.sales_system.service.impl;

import com.api.sales_system.dto.CategoryCreateDTO;
import com.api.sales_system.dto.CategoryResponseDTO;
import com.api.sales_system.dto.CategoryUpdateDTO;
import com.api.sales_system.entity.Category;
import com.api.sales_system.exception.ResourceAlreadyExistsException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.CategoryMapper;
import com.api.sales_system.repository.CategoryRepository;
import com.api.sales_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Optional<Category> categoryOpt = this.categoryRepository.findByName(categoryCreateDTO.getName());

        if (categoryOpt.isPresent()) throw  new ResourceAlreadyExistsException("Categoria con el nombre " + categoryCreateDTO.getName() + " ya existe en el sistema.");

        Category category = this.categoryMapper.toEntity(categoryCreateDTO);

        return this.categoryMapper.toResponseDTO(this.categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada."));

        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada."));

        return this.categoryMapper.toResponseDTO(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        Optional<Category> categoryOpt = this.categoryRepository.findByName(categoryUpdateDTO.getName());

        if (categoryOpt.isPresent()) throw  new ResourceAlreadyExistsException("Categoria con el nombre " + categoryUpdateDTO.getName() + " ya existe en el sistema.");

        categoryOpt.get().setName(categoryUpdateDTO.getName());
        categoryOpt.get().setDescription(categoryUpdateDTO.getDescription());
        categoryOpt.get().setActive(categoryUpdateDTO.isActive());

        return this.categoryMapper.toResponseDTO(this.categoryRepository.save(categoryOpt.get()));
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        if (categories.isEmpty()) return List.of();

        return this.categoryMapper.toResponseList(categories);
    }

    @Override
    public List<CategoryResponseDTO> getActiveCategories() {
        List<Category> categories = this.categoryRepository.findByActiveTrue();

        if (categories.isEmpty()) return List.of();

        return this.categoryMapper.toResponseList(categories);
    }
}