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
    private Long id;

    @Schema(description = "First name of the employee.", example = "Laura")
    private String firstName;

    @Schema(description = "Last name of the employee.", example = "Morales")
    private String lastName;

    @Schema(
            description = "Role assigned to the employee.",
            example = "ADMINISTRATOR",
            implementation = Role.class
    )
    private Role role;

    @Schema(description = "Username used by the employee to log in.", example = "laura.morales")
    private String userName;

}
