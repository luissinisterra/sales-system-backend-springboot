package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
