package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to reset an employee's password.")
public class ResetPasswordRequestDTO {

    @Schema(description = "The new password to be set for the employee.", example = "StrongPass2024!")
    @NotBlank(message = "La nueva contraseña es obligatoria.")
    private String newPassword;
}
