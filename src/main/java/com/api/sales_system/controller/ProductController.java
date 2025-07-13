package com.api.sales_system.controller;

import com.api.sales_system.dto.MessageResponseDTO;
import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import com.api.sales_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/products")
@Tag(name = "Products", description = "Endpoints for managing products in the Sales System")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with details such as name, price, stock, and provider."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data")
    })
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        ProductResponseDTO productResponseDTO = this.productService.createProduct(productCreateDTO);
        URI location = URI.create("/api/v2/products/" + productResponseDTO.getId());
        return ResponseEntity.created(location).body(productResponseDTO);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete product by ID",
            description = "Deletes a product using its unique ID. Requires that the product exists."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<MessageResponseDTO> deleteProduct(
            @Parameter(description = "ID of the product to delete", example = "1")
            @PathVariable Long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.ok(new MessageResponseDTO("El producto fué eliminado exitosamente."));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Retrieves product information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDTO> getProduct(
            @Parameter(description = "ID of the product to retrieve", example = "1")
            @PathVariable Long id) {
        ProductResponseDTO productResponseDTO = this.productService.getProductById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update product by ID",
            description = "Updates the product's information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Parameter(description = "ID of the product to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        ProductResponseDTO productResponseDTO = this.productService.updateProduct(id, productUpdateDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Retrieves a list of all products in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No products found in the system")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponseDTO = this.productService.getProducts();
        return ResponseEntity.ok(productResponseDTO);
    }

    // ==================== SEARCH METHODS ====================

    @GetMapping("/search")
    @Operation(
            summary = "Search products by name",
            description = "Searches for products by name using case-insensitive partial matching."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found successfully"),
            @ApiResponse(responseCode = "404", description = "No products found matching the search criteria")
    })
    public ResponseEntity<List<ProductResponseDTO>> searchProductsByName(
            @Parameter(description = "Product name to search for", example = "Laptop")
            @RequestParam String name) {
        List<ProductResponseDTO> products = this.productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    @Operation(
            summary = "Get products by category",
            description = "Retrieves all products belonging to a specific category."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No products found in the specified category")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(
            @Parameter(description = "Category name to filter by", example = "Electronics")
            @PathVariable String category) {
        List<ProductResponseDTO> products = this.productService.getProductsByCategoryName(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price-range")
    @Operation(
            summary = "Get products by price range",
            description = "Retrieves products within a specified price range (inclusive)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid price range parameters")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPriceRange(
            @Parameter(description = "Minimum price", example = "10.00")
            @RequestParam BigDecimal minPrice,
            @Parameter(description = "Maximum price", example = "100.00")
            @RequestParam BigDecimal maxPrice) {
        List<ProductResponseDTO> products = this.productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/low-stock")
    @Operation(
            summary = "Get products with low stock",
            description = "Retrieves products with stock quantity below the specified threshold."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Low stock products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No products found with low stock")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProductsWithLowStock(
            @Parameter(description = "Stock threshold", example = "10")
            @RequestParam(defaultValue = "10") Integer threshold) {
        List<ProductResponseDTO> products = this.productService.getProductsWithLowStock(threshold);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/out-of-stock")
    @Operation(
            summary = "Get out of stock products",
            description = "Retrieves all products that are currently out of stock (stock = 0)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Out of stock products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No out of stock products found")
    })
    public ResponseEntity<List<ProductResponseDTO>> getOutOfStockProducts() {
        List<ProductResponseDTO> products = this.productService.getOutOfStockProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/available")
    @Operation(
            summary = "Get available products",
            description = "Retrieves all products that are currently available in stock (stock > 0)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Available products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No available products found")
    })
    public ResponseEntity<List<ProductResponseDTO>> getAvailableProducts() {
        List<ProductResponseDTO> products = this.productService.getAvailableProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/top-expensive")
    @Operation(
            summary = "Get top expensive products",
            description = "Retrieves the most expensive products, limited by the specified count."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top expensive products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No products found")
    })
    public ResponseEntity<List<ProductResponseDTO>> getTopExpensiveProducts(
            @Parameter(description = "Number of products to retrieve", example = "5")
            @RequestParam(defaultValue = "10") Integer limit) {
        List<ProductResponseDTO> products = this.productService.getTopExpensiveProducts(limit);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories")
    @Operation(
            summary = "Get all product categories",
            description = "Retrieves a list of all unique product categories in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No categories found")
    })
    public ResponseEntity<List<String>> getProductCategories() {
        List<String> categories = this.productService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

}