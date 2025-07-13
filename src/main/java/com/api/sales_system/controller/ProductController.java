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
        URI location = URI.create("/api/v1/products/" + productResponseDTO.getId());
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
        ProductResponseDTO productResponseDTO = this.productService.getClientById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update product by ID",
            description = "Updates the product’s information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Parameter(description = "ID of the product to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        ProductResponseDTO productResponseDTO = this.productService.updateClient(id, productUpdateDTO);
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
}
