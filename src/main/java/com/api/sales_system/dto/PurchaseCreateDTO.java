package com.api.sales_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseCreateDTO {

    @NotNull(message = "El ID del proveedor es obligatorio.")
    private Long providerId;

    @NotNull(message = "El ID del empleado es obligatorio.")
    private Long employeeId;

    // Se calculará automáticamente en el backend
    private String purchaseDate; // ISO-8601 format (opcional si backend la genera)

    @NotNull(message = "La lista de productos no puede estar vacía.")
    @Size(min = 1, message = "Debe incluir al menos un producto en la compra.")
    private List<PurchaseDetailCreateDTO> purchaseDetails;

}
