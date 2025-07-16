package com.api.sales_system.mapper;

import com.api.sales_system.dto.SaleCreateDTO;
import com.api.sales_system.dto.SaleResponseDTO;
import com.api.sales_system.entity.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    Sale toEntity(SaleCreateDTO saleCreateDTO);

    SaleResponseDTO toResponseDTO(Sale sale);

    List<SaleResponseDTO> toResponseList(List<Sale> sales);
}
