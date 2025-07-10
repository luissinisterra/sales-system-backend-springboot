package com.api.sales_system.service;

import com.api.sales_system.dto.ClientCreateDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.dto.ClientUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    ClientResponseDTO createClient(ClientCreateDTO clientCreateDTO);
    void deleteClientById(Long id);
    ClientResponseDTO getClientById(Long id);
    ClientResponseDTO updateClient(Long id, ClientUpdateDTO clientUpdateDTO);
    List<ClientResponseDTO> getClients();
}
