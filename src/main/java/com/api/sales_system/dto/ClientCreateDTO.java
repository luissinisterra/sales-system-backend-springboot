package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object used to create a new client.")
public class ClientCreateDTO {

    @Schema(
            description = "Document number of the client (unique identifier). Must be at least 10 digits.",
            example = "1234567890"
    )
    @NotBlank(message = "El documento es obligatorio.")
    @Pattern(regexp = "^[0-9]{11,}$", message = "El documento debe contener solo números y tener más de 10 dígitos.")
    private String id;

    @Schema(description = "First name of the client.", example = "Juan")
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Schema(description = "Last name of the client.", example = "Pérez")
    @NotBlank(message = "El apellido es obligatorio.")
    private String lastName;

    @Schema(description = "Phone number of the client. Must be unique.", example = "3101234567")
    @NotBlank(message = "El número de celular es obligatorio.")
    private String phoneNumber;

    @Schema(description = "Email address of the client. Must be unique and valid.", example = "juan.perez@example.com")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Formato de correo electrónico inválido.")
    private String email;

    @Schema(description = "Age of the client. Must be a non-negative number.", example = "30")
    @Min(value = 0, message = "La edad no puede ser negativa.")
    private int age;
}
