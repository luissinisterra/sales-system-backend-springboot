package com.api.sales_system.dto;

import com.api.sales_system.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data returned after employee creation or query.")
public class EmployeeResponseDTO {

    @Schema(description = "Unique identifier of the employee.", example = "5")
    @NotNull(message = "El id es obligatorio.")
    @Positive(message = "El id debe ser un número positivo.")
    private Long id;

    @Schema(description = "First name of the employee.", example = "Laura")
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Schema(description = "Last name of the employee.", example = "Morales")
    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @Schema(
            description = "Role assigned to the employee.",
            example = "ADMINISTRATOR",
            implementation = Role.class
    )
    @NotNull(message = "El rol es obligatorio.")
    private Role role;

    @Schema(description = "Username used by the employee to log in.", example = "laura.morales")
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String userName;
}
