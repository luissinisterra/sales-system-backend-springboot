package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {

    private Long id;

    private ProviderResponseDTO provider;

    private EmployeeResponseDTO employee;

    private String purchaseDate;

    private BigDecimal totalAmount;

    private List<PurchaseDetailResponseDTO> purchaseDetails;

}
