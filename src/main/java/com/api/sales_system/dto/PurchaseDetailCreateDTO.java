package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailCreateDTO {

    @Schema(description = "Product name.", example = "Café Colombiano")
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @Schema(description = "Unit price of the product. Must be zero or greater.", example = "12900.50")
    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    private BigDecimal price;

    @Schema(
            description = "Product category ID. Must match one of the allowed category values.",
            example = "1"
    )
    @NotNull(message = "El ID de la categoría es obligatoria.")
    private Long categoryId;

    @Min(value = 1, message = "La cantidad debe ser mayor que cero.")
    private int quantity;

    @NotNull(message = "El precio unitario de la compra es obligatorio.")
    @Min(value = 0, message = "El precio unitario de la compra no puede ser negativo.")
    private BigDecimal unitPrice;

    @NotNull(message = "El precio total unitario de la compra es obligatorio.")
    @Min(value = 0, message = "El precio total unitario de la compra no puede ser negativo.")
    private BigDecimal subTotal;

}


