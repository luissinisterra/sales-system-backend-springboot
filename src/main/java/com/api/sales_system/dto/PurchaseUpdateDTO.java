package com.api.sales_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseUpdateDTO {

    @NotNull(message = "El ID del proveedor es obligatorio.")
    private Long providerId;

    @NotNull(message = "El ID del empleado es obligatorio.")
    private Long employeeId;

    @NotBlank(message = "La fecha de la compra es obligatoria.")
    private String purchaseDate;

    @NotNull(message = "El precio total de la compra es obligatorio.")
    @Min(value = 0, message = "El precio total de la compra no puede ser negativa.")
    private BigDecimal totalAmount;

    @NotNull(message = "La lista de productos no puede estar vacía.")
    @Size(min = 1, message = "Debe incluir al menos un producto en la compra.")
    private List<PurchaseDetailCreateDTO> purchaseDetails;

}
