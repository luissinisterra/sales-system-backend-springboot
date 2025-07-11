package com.api.sales_system.dto;

import com.api.sales_system.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO used to represent product data returned by the API.")
public class ProductResponseDTO {

    @Schema(description = "Unique identifier of the product.", example = "101")
    @NotNull(message = "El id no puede ser nulo.")
    @Positive(message = "El id debe ser un número positivo.")
    private Long id;

    @Schema(description = "Name of the product.", example = "Café Premium")
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @Schema(description = "Unit price of the product.", example = "8900.0")
    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    private double price;

    @Schema(
            description = "Category of the product.",
            example = "BEVERAGE",
            implementation = Category.class
    )
    @NotNull(message = "La categoría es obligatoria.")
    private Category category;

    @Schema(description = "Stock available in inventory.", example = "45")
    @Min(value = 0, message = "Las existencias no pueden ser negativas.")
    private int stock;
}
