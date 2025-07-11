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
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product product = this.productMapper.toEntity(productCreateDTO);
        return this.productMapper.toResponseDTO(this.productRepository.save(product));
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty()) throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        this.productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO getClientById(Long id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty()) throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        return this.productMapper.toResponseDTO(productOpt.get());
    }

    @Override
    public ProductResponseDTO updateClient(Long id, ProductUpdateDTO productUpdateDTO) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty()) throw new ResourceNotFoundException("El producto con el id: " + id + " no fué encontrado.");

        productOpt.get().setName(productUpdateDTO.getName());
        productOpt.get().setPrice(productUpdateDTO.getPrice());
        productOpt.get().setCategory(productUpdateDTO.getCategory());
        productOpt.get().setStock(productUpdateDTO.getStock());

        return this.productMapper.toResponseDTO(this.productRepository.save(productOpt.get()));
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        List<Product> products = this.productRepository.findAll();

        if (products.isEmpty()) throw new ResourceNotFoundException("No se encontraron productos registrados en el sistéma.");

        return this.productMapper.toResponseList(products);
    }
}
