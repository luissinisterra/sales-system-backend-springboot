package com.api.sales_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {
    @NotBlank(message = "La contraseña actual es obligatoria.")
    private String currentPassword;

    @NotBlank(message = "La nueva contraseña es obligatoria.")
    private String newPassword;
}

