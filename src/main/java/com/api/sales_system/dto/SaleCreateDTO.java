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
public class SaleCreateDTO {

    @NotNull(message = "El ID del cliente es obligatorio.")
    private String clientId;

    @NotNull(message = "El ID del empleado es obligatorio.")
    private Long employeeId;

    @NotBlank(message = "La fecha de la venta es obligatoria.")
    private String saleDate;

    @NotNull(message = "El precio total de la venta es obligatorio.")
    @Min(value = 0, message = "El precio total de la venta no puede ser negativa.")
    private BigDecimal totalAmount;

    @NotNull(message = "La lista de productos no puede estar vacía.")
    @Size(min = 1, message = "Debe incluir al menos un producto en la venta.")
    private List<SaleDetailCreateDTO> saleDetails;

}
