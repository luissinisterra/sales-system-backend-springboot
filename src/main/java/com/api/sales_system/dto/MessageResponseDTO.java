package com.api.sales_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Simple response message wrapper used for success or info messages.")
public class MessageResponseDTO {

    @Schema(description = "Text message returned by the API.", example = "El recurso fue eliminado correctamente.")
    private String message;
}
