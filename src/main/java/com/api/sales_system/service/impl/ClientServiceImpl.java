package com.api.sales_system.service.Impl;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {   

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        return null;
    }

    @Override
    public void deleteClientById(Long id) {

    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        return null;
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        return List.of();
    }
}
