package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {

    private Long id;

    private String providerName;

    private String employeeName;

    private LocalDateTime purchaseDate;

    private BigDecimal totalAmount;

    private List<PurchaseDetailResponseDTO> purchaseDetails;

}
