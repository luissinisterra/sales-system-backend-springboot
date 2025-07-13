package com.api.sales_system.dto;

import java.time.LocalDateTime;

public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
