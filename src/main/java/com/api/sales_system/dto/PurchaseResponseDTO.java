package com.api.sales_system.dto;

import com.api.sales_system.entity.Purchase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
