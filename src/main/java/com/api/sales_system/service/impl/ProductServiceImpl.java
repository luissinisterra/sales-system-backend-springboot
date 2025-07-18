package com.api.sales_system.service.impl;

import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import com.api.sales_system.entity.Category;
import com.api.sales_system.entity.Product;
import com.api.sales_system.entity.Provider;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.ProductMapper;
import com.api.sales_system.repository.CategoryRepository;
import com.api.sales_system.repository.ProductRepository;
import com.api.sales_system.repository.ProviderRepository;
import com.api.sales_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProviderRepository providerRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProviderRepository providerRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.providerRepository = providerRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        Category category = this.categoryRepository.findById(productCreateDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada."));

        Provider provider = this.providerRepository.findById(productCreateDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider no encontrado."));

        Product product = this.productMapper.toEntity(productCreateDTO);

        product.setCategory(category);
        product.setProvider(provider);

        return this.productMapper.toResponseDTO(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

        this.productRepository.delete(product);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));


        return this.productMapper.toResponseDTO(product);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

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
    public List<ProductResponseDTO> getProductsByCategoryName(String categoryName) {
        List<Product> products = this.productRepository.findByCategory_NameIgnoreCase(categoryName);

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
        List<Product> products = this.productRepository.findTopByOrderByPriceDesc();

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
}
