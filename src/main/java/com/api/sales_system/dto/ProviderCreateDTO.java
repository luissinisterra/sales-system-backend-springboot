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

    @NotNull(message = "El ID es obligatorio.")
    private Long id;

    @NotBlank(message = "El nombre de la empresa es obligatorio.")
    private String companyName;

    @NotBlank(message = "La persona de contacto es obligatoria.")
    private String contactPerson;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    private String phoneNumber;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Formato de correo electrónico inválido.")
    private String email;

    @NotBlank(message = "La dirección es obligatoria.")
    private String address;

    @NotBlank(message = "La ciudad es obligatoria.")
    private String city;

    @NotBlank(message = "El país es obligatorio.")
    private String country;

    @NotNull(message = "La lista de productos del proveedor no puede estar vacía.")
    private List<ProviderProduct> providerProducts;

}