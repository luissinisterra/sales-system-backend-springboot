package com.api.sales_system.dto;

import com.api.sales_system.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO used to represent product data returned by the API.")
public class ProductResponseDTO {

    @Schema(description = "Unique identifier of the product.", example = "101")
    private Long id;

    @Schema(description = "Name of the product.", example = "Café Premium")
    private String name;

    @Schema(description = "Unit price of the product.", example = "8900.0")
    private double price;

    @Schema(
            description = "Category of the product.",
            example = "BEVERAGE"
    )
    private Category category;

    @Schema(description = "Stock available in inventory.", example = "45")
    private int stock;

}
