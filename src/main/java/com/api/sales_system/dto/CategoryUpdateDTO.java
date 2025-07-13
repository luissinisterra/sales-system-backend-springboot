package com.api.sales_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @NotBlank(message = "La descripción es obligatoria.")
    private String description;

}
