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

    private ClientResponseDTO client;

    private EmployeeResponseDTO employee;

    private String saleDate;

    private BigDecimal totalAmount;

    private List<SaleDetailResponseDTO> saleDetails;

}
