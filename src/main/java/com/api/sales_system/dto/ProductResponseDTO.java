package com.api.sales_system.dto;

import com.api.sales_system.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    @NotBlank(message = "El id no puede ser negativo.")
    private Long id;

    @NotBlank(message = "El nombre es obligaotorio.")
    private String name;

    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    private double price;

    @NotNull(message = "La categoría es obligatoria.")
    private Category category;

    @Min(value = 0, message = "Las existencias no pueden ser negativas.")
    private int stock;

}
