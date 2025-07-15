package com.api.sales_system.dto;

import com.api.sales_system.entity.ProviderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderResponseDTO {

    private Long id;

    private String companyName;

    private String contactPerson;

    private String phoneNumber;

    private String email;

    private String address;

    private String city;

    private String country;

    /*private List<ProviderProduct> providerProducts;*/

}