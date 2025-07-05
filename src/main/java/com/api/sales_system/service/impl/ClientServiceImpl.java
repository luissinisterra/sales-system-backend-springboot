package com.api.sales_system.service.impl;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.entity.Client;
import com.api.sales_system.exception.ResourceNotFound;
import com.api.sales_system.mapper.ClientMapper;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        Client client = this.clientMapper.toEntity(clientRequestDTO);
        return this.clientMapper.toResponseDTO(this.clientRepository.save(client));
    }

    @Override
    public void deleteClientById(Long id) {
        Optional<Client> clientOpt = this.clientRepository.findById(id);

        if(clientOpt.isEmpty()) throw new ResourceNotFound("El cliente con el id: " + id + " no fué encontrado.");

        this.clientRepository.deleteById(id);
    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        Optional<Client> clientOpt = this.clientRepository.findById(id);

        if (clientOpt.isEmpty()) throw new ResourceNotFound("El cliente con el id: " + id + " no fué encontrado.");

        return this.clientMapper.toResponseDTO(clientOpt.get());
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        List<Client> clients = this.clientRepository.findAll();

        if (clients.isEmpty()) throw new ResourceNotFound("Aun no hay clientes registrados en el sistema.");

        return this.clientMapper.toResponseList(clients);
    }
}
