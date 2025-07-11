package com.api.sales_system.dto;

import com.api.sales_system.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO used to update an existing employee's information.")
public class EmployeeUpdateDTO {

    @Schema(description = "First name of the employee.", example = "Andrés")
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Schema(description = "Last name of the employee.", example = "Sánchez")
    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @Schema(
            description = "Role of the employee. Can be ADMINISTRATOR or CASHIER.",
            example = "CASHIER",
            implementation = Role.class
    )
    @NotNull(message = "El rol es obligatorio.")
    private Role role;

    @Schema(description = "Unique username for the employee.", example = "andres.sanchez")
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String userName;
}
