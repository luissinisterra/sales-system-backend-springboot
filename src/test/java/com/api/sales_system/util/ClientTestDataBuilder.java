package com.api.sales_system.util;

import com.api.sales_system.dto.ClientCreateDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.entity.Client;

public class ClientTestDataBuilder {

    public static Client buildClient() {
        return new Client(
                "1234567890",
                "John",
                "Doe",
                "3001234567",
                "john.doe@example.com",
                30
        );
    }

    public static ClientCreateDTO buildClientCreateDTO() {
        ClientCreateDTO clientCreateDTO = new ClientCreateDTO();
        clientCreateDTO.setId("1234567890");
        clientCreateDTO.setFirstName("John");
        clientCreateDTO.setLastName("Doe");
        clientCreateDTO.setPhoneNumber("3001234567");
        clientCreateDTO.setEmail("john.doe@example.com");
        clientCreateDTO.setAge(30);
        return clientCreateDTO;
    }

    public static ClientResponseDTO buildClientResponseDTO() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId("1234567890");
        clientResponseDTO.setFirstName("John");
        clientResponseDTO.setLastName("Doe");
        clientResponseDTO.setPhoneNumber("3001234567");
        clientResponseDTO.setEmail("john.doe@example.com");
        clientResponseDTO.setAge(30);
        return clientResponseDTO;
    }
}
