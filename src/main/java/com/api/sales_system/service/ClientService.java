package com.api.sales_system.service;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);
    void deleteClientById(Long id);
    ClientResponseDTO getClientById(Long id);
    List<ClientResponseDTO> getClients();
}
