package com.api.sales_system.controller;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.service.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@Tag(name = "Clients", description = "Endpoints for managing clients in the Sales System")
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new client",
            description = "Registers a new client with unique email and phone number. Validates input data such as document ID, name, phone, email, and age."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data"),
            @ApiResponse(responseCode = "409", description = "Conflict - email or phone number already exists")
    })
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = this.clientServiceImpl.createClient(clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a client by ID",
            description = "Deletes a client using their unique document ID. Requires the ID to exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Void> deleteClient(
            @Parameter(description = "Document ID of the client to delete", example = "1234567890")
            @PathVariable Long id) {
        this.clientServiceImpl.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get client by ID",
            description = "Retrieves the client’s information using their unique document ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<ClientResponseDTO> getClient(
            @Parameter(description = "Document ID of the client to retrieve", example = "1234567890")
            @PathVariable Long id) {
        ClientResponseDTO clientResponseDTO = this.clientServiceImpl.getClientById(id);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all clients",
            description = "Retrieves a list of all clients registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of clients retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "There're not clients at system.")
    })
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        List<ClientResponseDTO> clientsResponseDTOS = this.clientServiceImpl.getClients();
        return ResponseEntity.ok(clientsResponseDTOS);
    }
}
