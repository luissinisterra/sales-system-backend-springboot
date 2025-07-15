package com.api.sales_system.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.sales_system.dto.ClientCreateDTO;
import com.api.sales_system.entity.Client;
import com.api.sales_system.mapper.ClientMapper;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.util.ClientTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void createClientTest() {
        // Having
        ClientCreateDTO clientRequestDTO = ClientTestDataBuilder.buildClientCreateDTO();
        Client client = ClientTestDataBuilder.buildClient();

        // Mocking the repository save
        when(this.clientRepository.save(any(Client.class))).thenReturn(client);

        // Mocking the mapper (as you probably map the request to entity inside the service)
        when(this.clientMapper.toEntity(clientRequestDTO)).thenReturn(client);

        // Mocking the response mapping
        when(this.clientMapper.toResponseDTO(client)).thenReturn(ClientTestDataBuilder.buildClientResponseDTO());

        // When
        var response = clientService.createClient(clientRequestDTO);

        // Then
        assertEquals("1234567890", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("3001234567", response.getPhoneNumber());
        assertEquals("john.doe@example.com", response.getEmail());
        assertEquals(30, response.getAge());

        // Verify repository was called
        verify(this.clientRepository).save(any(Client.class));
    }
}