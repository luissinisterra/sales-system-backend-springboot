package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object used to update an existing client's information.")
public class ClientUpdateDTO {

    @Schema(description = "First name of the client.", example = "Carlos")
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Schema(description = "Last name of the client.", example = "Ramírez")
    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @Schema(description = "Phone number of the client. Must be unique.", example = "3112233445")
    @NotBlank(message = "El número de celular es obligatorio.")
    private String phoneNumber;

    @Schema(description = "Email address of the client. Must be valid and unique.", example = "carlos.ramirez@example.com")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Formato de correo electrónico inválido.")
    private String email;

    @Schema(description = "Age of the client. Must be zero or greater.", example = "45")
    @Min(value = 0, message = "La edad no puede ser negativa.")
    private int age;
}
