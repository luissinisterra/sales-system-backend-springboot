package com.api.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}