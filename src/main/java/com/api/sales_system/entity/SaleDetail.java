package com.api.sales_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_details")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(value = 0, message = "La cantidad no puede ser negativa.")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Min(value = 0, message = "El precio unitario no puede ser negativo.")
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Min(value = 0, message = "El precio subtotal no puede ser negativo.")
    @Column(name = "sub_total", nullable = false)
    private double subTotal;

}
