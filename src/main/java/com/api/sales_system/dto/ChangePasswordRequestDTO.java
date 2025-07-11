package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for changing employee password")
public class ChangePasswordRequestDTO {

    @Schema(description = "Current password of the employee", example = "oldpass123")
    @NotBlank(message = "La contraseña actual es obligatoria.")
    private String currentPassword;

    @Schema(description = "New password to be set", example = "newpass456")
    @NotBlank(message = "La nueva contraseña es obligatoria.")
    @Min(value = 8, message = "La nueva contraseña debe tener al menos 8 caracteres.")
    private String newPassword;

}


