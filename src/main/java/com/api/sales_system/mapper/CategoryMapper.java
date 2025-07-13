package com.api.sales_system.mapper;

import com.api.sales_system.dto.CategoryCreateDTO;
import com.api.sales_system.dto.CategoryResponseDTO;
import com.api.sales_system.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryCreateDTO categoryCreateDTO);

    CategoryResponseDTO toResponseDTO(Category category);

    List<CategoryResponseDTO> toResponseList(List<Category> categories);
}
