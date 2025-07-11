package com.api.sales_system.service;

import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO);
    void deleteProductById(Long id);
    ProductResponseDTO getClientById(Long id);
    ProductResponseDTO updateClient(Long id, ProductUpdateDTO productUpdateDTO);
    List<ProductResponseDTO> getProducts();
}
