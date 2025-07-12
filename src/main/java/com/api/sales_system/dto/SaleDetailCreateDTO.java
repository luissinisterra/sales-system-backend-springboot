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
public class SaleDetailCreateDTO {

    @NotNull(message = "El ID del producto es obligatorio.")
    private Long productId;

    @Min(value = 1, message = "La cantidad debe ser mayor que 0.")
    private int quantity;

    @NotNull(message = "El precio unitario es obligatorio.")
    private BigDecimal unitPrice;

}
