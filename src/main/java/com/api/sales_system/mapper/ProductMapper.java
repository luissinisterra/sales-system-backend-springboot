package com.api.sales_system.mapper;

import com.api.sales_system.dto.ProductCreateDTO;
import com.api.sales_system.dto.ProductResponseDTO;
import com.api.sales_system.dto.ProductUpdateDTO;
import com.api.sales_system.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductCreateDTO productCreateDTO);
    ProductResponseDTO toResponseDTO(Product product);
    List<ProductResponseDTO> toResponseList(List<Product> products);
}
