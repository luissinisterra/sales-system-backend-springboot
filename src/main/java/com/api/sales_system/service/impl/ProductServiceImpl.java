package com.api.sales_system.service.impl;

import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import com.api.sales_system.entity.Product;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.ProductMapper;
import com.api.sales_system.repository.ProductRepository;
import com.api.sales_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product product = this.productMapper.toEntity(productCreateDTO);
        return this.productMapper.toResponseDTO(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty())
            throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        this.productRepository.deleteById(id);
    }

    // Corregido: cambié el nombre del método de getClientById a getProductById
    @Override
    public ProductResponseDTO getProductById(Long id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty())
            throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        return this.productMapper.toResponseDTO(productOpt.get());
    }

    // Corregido: cambié el nombre del método de updateClient a updateProduct
    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty())
            throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        Product product = productOpt.get();
        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setCategory(productUpdateDTO.getCategory());
        product.setStock(productUpdateDTO.getStock());

        return this.productMapper.toResponseDTO(this.productRepository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        List<Product> products = this.productRepository.findAll();

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    // ==================== MÉTODOS DE BÚSQUEDA ====================

    @Override
    public List<ProductResponseDTO> searchProductsByName(String name) {
        List<Product> products = this.productRepository.findByNameContainingIgnoreCase(name);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        List<Product> products = this.productRepository.findByCategoryIgnoreCase(category);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getProductsByProvider(Long providerId) {
        List<Product> products = this.productRepository.findByProviderId(providerId);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("El precio mínimo no puede ser mayor al precio máximo");
        }

        List<Product> products = this.productRepository.findByPriceBetween(minPrice, maxPrice);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getProductsWithLowStock(Integer threshold) {
        List<Product> products = this.productRepository.findByStockLessThan(threshold);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getOutOfStockProducts() {
        List<Product> products = this.productRepository.findByStock(0);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getAvailableProducts() {
        List<Product> products = this.productRepository.findByStockGreaterThan(0);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<ProductResponseDTO> getTopExpensiveProducts(Integer limit) {
        List<Product> products = this.productRepository.findTopByOrderByPriceDesc(limit);

        if (products.isEmpty())
            return List.of();

        return this.productMapper.toResponseList(products);
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = this.productRepository.findDistinctCategories();

        if (categories.isEmpty())
            return List.of();

        return categories;
    }

    @Override
    public Long countProductsByCategory(String category) {
        return this.productRepository.countByCategoryIgnoreCase(category);
    }
}