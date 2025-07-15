package com.api.sales_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponseDTO {

    private Long id;

    private Long providerId;

    private Long productId;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal subTotal;

}
