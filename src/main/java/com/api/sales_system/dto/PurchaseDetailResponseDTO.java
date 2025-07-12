package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponseDTO {

    private String productName;

    private int quantity;

    private BigDecimal priceAtPurchase;

    private BigDecimal subtotal;

}
