package com.api.sales_system.controller;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl){
        this.clientServiceImpl = clientServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO){
        ClientResponseDTO clientResponseDTO = this.clientServiceImpl.createClient(clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        this.clientServiceImpl.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id){
        ClientResponseDTO clientResponseDTO = this.clientServiceImpl.getClientById(id);
        return ResponseEntity.ok(clientResponseDTO);
    }

}
