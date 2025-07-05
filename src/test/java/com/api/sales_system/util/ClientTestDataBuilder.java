package com.api.sales_system.util;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.entity.Client;

public class ClientTestDataBuilder {

    public static Client buildClient() {
        return new Client(
                1234567890L,
                "John",
                "Doe",
                "3001234567",
                "john.doe@example.com",
                30
        );
    }

    public static ClientRequestDTO buildClientRequestDTO() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setId(1234567890L);
        clientRequestDTO.setFirstName("John");
        clientRequestDTO.setLastName("Doe");
        clientRequestDTO.setPhoneNumber("3001234567");
        clientRequestDTO.setEmail("john.doe@example.com");
        clientRequestDTO.setAge(30);
        return clientRequestDTO;
    }

    public static ClientResponseDTO buildClientResponseDTO() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId(1234567890L);
        clientResponseDTO.setFirstName("John");
        clientResponseDTO.setLastName("Doe");
        clientResponseDTO.setPhoneNumber("3001234567");
        clientResponseDTO.setEmail("john.doe@example.com");
        clientResponseDTO.setAge(30);
        return clientResponseDTO;
    }
}
