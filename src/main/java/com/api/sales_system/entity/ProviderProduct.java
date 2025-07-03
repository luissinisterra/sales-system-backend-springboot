package com.api.sales_system.entity;

import com.api.sales_system.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provider_products")
public class ProviderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligaotorio.")
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 0, message = "El precio no puede ser un número negativo.")
    @Column(name = "price", nullable = false)
    private double price;

    @NotBlank(message = "La categoría es obligatoria.")
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

}
