package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Client data returned by the API after creation or retrieval.")
public class ClientResponseDTO {

    @Schema(
            description = "Client document number (unique identifier).",
            example = "1234567890"
    )
    private String id;

    @Schema(description = "First name of the client.", example = "Ana")
    private String firstName;

    @Schema(description = "Last name of the client.", example = "Gómez")
    private String lastName;

    @Schema(description = "Phone number of the client.", example = "3209876543")
    private String phoneNumber;

    @Schema(description = "Email address of the client.", example = "ana.gomez@example.com")
    private String email;

    @Schema(description = "Age of the client.", example = "28")
    private int age;

}
