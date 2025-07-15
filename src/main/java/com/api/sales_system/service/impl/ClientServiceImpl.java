package com.api.sales_system.service.impl;

import com.api.sales_system.dto.ClientCreateDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.dto.ClientUpdateDTO;
import com.api.sales_system.entity.Client;
import com.api.sales_system.exception.ResourceAlreadyExistsException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.ClientMapper;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ClientResponseDTO createClient(ClientCreateDTO clientCreateDTO) {
        boolean exists = clientRepository.existsById(clientCreateDTO.getId());
        if (exists) {
            throw new ResourceAlreadyExistsException("Cliente con el id: " + clientCreateDTO.getId() + " ya existe en el sistema.");
        }

        Client client = clientMapper.toEntity(clientCreateDTO);
        return clientMapper.toResponseDTO(clientRepository.save(client));
    }

    @Override
    @Transactional
    public void deleteClientById(String id) {
        Client client = this.clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con el id: " + id + " no fue encontrado."));

        this.clientRepository.delete(client);
    }


    @Override
    public ClientResponseDTO getClientById(String id) {
        Optional<Client> clientOpt = this.clientRepository.findById(id);

        if (clientOpt.isEmpty()) throw new ResourceNotFoundException("El cliente con el id: " + id + " no fué encontrado.");

        return this.clientMapper.toResponseDTO(clientOpt.get());
    }

    @Override
    @Transactional
    public ClientResponseDTO updateClient(String id, ClientUpdateDTO clientUpdateDTO){
        Optional<Client> clientOpt = this.clientRepository.findById(id);

        if (clientOpt.isEmpty()) throw new ResourceNotFoundException("El cliente con el id: " + id + " no fué encontrado.");

        clientOpt.get().setFirstName(clientUpdateDTO.getFirstName());
        clientOpt.get().setLastName(clientUpdateDTO.getLastName());
        clientOpt.get().setPhoneNumber(clientUpdateDTO.getPhoneNumber());
        clientOpt.get().setEmail(clientUpdateDTO.getEmail());
        clientOpt.get().setAge(clientUpdateDTO.getAge());

        return this.clientMapper.toResponseDTO(this.clientRepository.save(clientOpt.get()));
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        List<Client> clients = this.clientRepository.findAll();

        if (clients.isEmpty()) return List.of();

        return this.clientMapper.toResponseList(clients);
    }
}
