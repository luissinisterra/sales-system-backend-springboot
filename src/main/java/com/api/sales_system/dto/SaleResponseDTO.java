package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDTO {

    private Long id;

    private Long clientId;

    private Long employeeId;

    private String saleDate;

    private BigDecimal totalAmount;

    private List<SaleDetailCreateDTO> saleDetails;

}
