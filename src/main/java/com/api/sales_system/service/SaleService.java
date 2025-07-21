package com.api.sales_system.service;

import com.api.sales_system.dto.SaleCreateDTO;
import com.api.sales_system.dto.SaleResponseDTO;

import java.util.List;

public interface SaleService {
    SaleResponseDTO createSale(SaleCreateDTO saleCreateDTO);
    void deleteSaleById(Long id);
    SaleResponseDTO getSaleById(Long id);
    List<SaleResponseDTO> getSales();
}
