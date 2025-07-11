package com.api.sales_system.dto;

import com.api.sales_system.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @NotNull(message = "El rol es obligatorio.")
    private Role role;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String userName;

}
