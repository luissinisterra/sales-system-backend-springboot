package com.api.sales_system.controller;

import com.api.sales_system.dto.CategoryCreateDTO;
import com.api.sales_system.dto.CategoryResponseDTO;
import com.api.sales_system.dto.CategoryUpdateDTO;
import com.api.sales_system.dto.MessageResponseDTO;
import com.api.sales_system.service.CategoryService;
import com.api.sales_system.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/categories")
@Tag(name = "Categories", description = "Endpoints for managing categories in the Sales System")
public class CategoryController {

    private final CategoryService categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Registers a new category with unique name and description. Validates input data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data"),
            @ApiResponse(responseCode = "409", description = "Conflict - name or description already exists")
    })
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateDTO categoryRequestDTO) {
        CategoryResponseDTO categoryResponseDTO = this.categoryServiceImpl.createCategory(categoryRequestDTO);
        URI location = URI.create("/api/v2/categories/" + categoryResponseDTO.getId());
        return ResponseEntity.created(location).body(categoryResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a category by ID",
            description = "Deletes a category using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<MessageResponseDTO> deleteCategory(
            @Parameter(description = "ID of the category to delete", example = "1")
            @PathVariable Long id) {
        this.categoryServiceImpl.deleteCategoryById(id);
        return ResponseEntity.ok(new MessageResponseDTO("La categoría fue eliminada exitosamente."));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get category by ID",
            description = "Retrieves the category’s information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryResponseDTO> getCategory(
            @Parameter(description = "ID of the category to retrieve", example = "1")
            @PathVariable Long id) {
        CategoryResponseDTO categoryResponseDTO = this.categoryServiceImpl.getCategoryById(id);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category by ID",
            description = "Updates the category’s information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @Parameter(description = "ID of the category to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        CategoryResponseDTO categoryResponseDTO = this.categoryServiceImpl.updateCategory(id, categoryUpdateDTO);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieves a list of all categories registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "There are no categories in the system")
    })
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        List<CategoryResponseDTO> categoriesResponseDTOS = this.categoryServiceImpl.getCategories();
        return ResponseEntity.ok(categoriesResponseDTOS);
    }
}
