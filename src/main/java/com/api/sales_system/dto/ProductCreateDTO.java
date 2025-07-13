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
@Schema(description = "DTO used to create a new product in the system.")
public class ProductCreateDTO {

    @Schema(description = "Product name.", example = "Café Colombiano")
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @Schema(description = "Unit price of the product. Must be zero or greater.", example = "12900.50")
    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    private double price;

    @Schema(
            description = "Product category. Must match one of the allowed enum values.",
            example = "BEVERAGE"
    )
    @NotNull(message = "La categoría es obligatoria.")
    private Category category;

    @Schema(description = "Available stock of the product.", example = "150")
    @Min(value = 0, message = "Las existencias no pueden ser negativas.")
    private int stock;
}
