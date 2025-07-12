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
public class SaleCreateDTO {

    @NotNull(message = "El ID del cliente es obligatorio.")
    private Long clientId;

    @NotNull(message = "El ID del empleado es obligatorio.")
    private Long employeeId;

    @Size(min = 1, message = "Debe incluir al menos un producto en la venta.")
    private List<SaleDetailCreateDTO> saleDetails;
}
