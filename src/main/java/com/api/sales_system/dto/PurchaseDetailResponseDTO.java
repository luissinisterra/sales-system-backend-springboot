package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponseDTO {

    private Long id;

    private ProductResponseDTO product;

    private int quantity;

    private BigDecimal purchasePrice;

    private BigDecimal subTotal;

}
