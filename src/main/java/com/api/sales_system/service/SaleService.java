package com.api.sales_system.service;

import com.api.sales_system.dto.SaleCreateDTO;
import com.api.sales_system.dto.SaleResponseDTO;
import com.api.sales_system.dto.SaleUpdateDTO;

import java.util.List;

public interface SaleService {
    SaleResponseDTO createSale(SaleCreateDTO saleCreateDTO);
    void deleteSaleById(Long id);
    SaleResponseDTO getSaleById(Long id);
    //SaleResponseDTO updateSale(Long id, SaleUpdateDTO saleUpdateDTO);
    List<SaleResponseDTO> getSales();
}
