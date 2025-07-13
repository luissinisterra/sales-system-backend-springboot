package com.api.sales_system.dto;

import com.api.sales_system.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO used to update product information.")
public class ProductUpdateDTO {

    @Schema(description = "Updated name of the product.", example = "Chocolate 70% Cacao")
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @Schema(description = "Updated unit price. Must be non-negative.", example = "13450.00")
    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    private double price;

    @Schema(
            description = "Updated product category.",
            example = "FOOD"
    )
    @NotNull(message = "La categoría es obligatoria.")
    private Category category;

    @Schema(description = "Updated available stock.", example = "120")
    @Min(value = 0, message = "Las existencias no pueden ser negativas.")
    private int stock;
}
