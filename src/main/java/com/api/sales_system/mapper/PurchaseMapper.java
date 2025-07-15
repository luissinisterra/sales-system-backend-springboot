package com.api.sales_system.mapper;

import com.api.sales_system.dto.PurchaseCreateDTO;
import com.api.sales_system.dto.PurchaseResponseDTO;
import com.api.sales_system.entity.Purchase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    Purchase toEntity(PurchaseCreateDTO purchaseCreateDTO);
    PurchaseResponseDTO toResponseDTO(Purchase purchase);
    List<PurchaseResponseDTO> toResponseList(List<Purchase> purchases);
}
