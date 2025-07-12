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
public class PurchaseDetailCreateDTO {

    @NotNull(message = "El ID del producto es obligatorio.")
    private Long productId;

    @Min(value = 1, message = "La cantidad debe ser mayor que cero.")
    private int quantity;

    @NotNull(message = "El precio de compra es obligatorio.")
    @Min(value = 0, message = "El precio no puede ser negativo.")
    private BigDecimal priceAtPurchase;
}
