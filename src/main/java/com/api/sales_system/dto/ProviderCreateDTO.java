package com.api.sales_system.dto;

import com.api.sales_system.entity.ProviderProduct;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderCreateDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @NotBlank(message = "El número de celular es obligatorio.")
    private String phoneNumber;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Formato de correo electrónico inválido.")
    private String email;

    @NotNull(message = "La lista de productos del proveedor no pueden estar vacia.")
    private List<ProviderProduct> providerProducts;

}
