package com.api.sales_system.service;

import com.api.sales_system.dto.PurchaseCreateDTO;
import com.api.sales_system.dto.PurchaseResponseDTO;
import com.api.sales_system.dto.PurchaseUpdateDTO;

import java.util.List;

public interface PurchaseService {
    PurchaseResponseDTO createPurchase(PurchaseCreateDTO purchaseCreateDTO);
    void deletePurchaseById(Long id);
    PurchaseResponseDTO getPurchaseById(Long id);
    //PurchaseResponseDTO updatePurchase(Long id, PurchaseUpdateDTO purchaseUpdateDTO);
    List<PurchaseResponseDTO> getPurchases();
}
