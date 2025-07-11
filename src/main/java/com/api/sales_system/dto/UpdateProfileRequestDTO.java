package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to update an employee's profile information.")
public class UpdateProfileRequestDTO {

    @Schema(description = "First name of the employee.", example = "Camila")
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Schema(description = "Last name of the employee.", example = "Ríos")
    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @Schema(description = "Username of the employee.", example = "camila.rios")
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String userName;
}
