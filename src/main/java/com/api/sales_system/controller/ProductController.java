package com.api.sales_system.controller;

import com.api.sales_system.dto.MessageResponseDTO;
import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import com.api.sales_system.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        ProductResponseDTO productResponseDTO = this.productService.createProduct(productCreateDTO);
        URI location = URI.create("/api/v1/products/" + productResponseDTO.getId());
        return ResponseEntity.created(location).body(productResponseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.ok(new MessageResponseDTO("El producto fué eliminado exitosamente."));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = this.productService.getClientById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        ProductResponseDTO productResponseDTO = this.productService.updateClient(id, productUpdateDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponseDTO = this.productService.getProducts();
        return ResponseEntity.ok(productResponseDTO);
    }

}
