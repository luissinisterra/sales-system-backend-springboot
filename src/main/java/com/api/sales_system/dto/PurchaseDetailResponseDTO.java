package com.api.sales_system.dto;

import com.api.sales_system.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponseDTO {

    private Long id;

    private String productName;

    private BigDecimal price;

    private CategoryResponseDTO category;

    private int quantity;

    private BigDecimal subTotal;

}
