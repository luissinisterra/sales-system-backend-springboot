package com.api.sales_system.service;

import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    // Métodos CRUD básicos
    ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO);
    void deleteProductById(Long id);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO);
    List<ProductResponseDTO> getProducts();

    // Métodos de búsqueda y filtrado
    List<ProductResponseDTO> searchProductsByName(String name);
    List<ProductResponseDTO> getProductsByCategory(String category);
    List<ProductResponseDTO> getProductsByProvider(Long providerId);
    List<ProductResponseDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<ProductResponseDTO> getProductsWithLowStock(Integer threshold);
    List<ProductResponseDTO> getOutOfStockProducts();
    List<ProductResponseDTO> getAvailableProducts();
    List<ProductResponseDTO> getTopExpensiveProducts(Integer limit);
    List<String> getAllCategories();
    Long countProductsByCategory(String category);
}