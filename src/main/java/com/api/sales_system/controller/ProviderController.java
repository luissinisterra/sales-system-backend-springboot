package com.api.sales_system.controller;

import com.api.sales_system.dto.MessageResponseDTO;
import com.api.sales_system.dto.ProviderCreateDTO;
import com.api.sales_system.dto.ProviderResponseDTO;
import com.api.sales_system.dto.ProviderUpdateDTO;
import com.api.sales_system.service.ProviderService;
import com.api.sales_system.service.impl.ProviderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/providers")
@Tag(name = "Providers", description = "Endpoints for managing providers in the Sales System")
public class ProviderController {

    private final ProviderService providerServiceImpl;

    @Autowired
    public ProviderController(ProviderServiceImpl providerServiceImpl) {
        this.providerServiceImpl = providerServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new provider",
            description = "Registers a new provider with unique email and company information. Validates input data such as company name, contact person, phone, email, and address."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provider created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data"),
            @ApiResponse(responseCode = "409", description = "Conflict - email or company name already exists")
    })
    public ResponseEntity<ProviderResponseDTO> createProvider(@Valid @RequestBody ProviderCreateDTO providerCreateDTO) {
        ProviderResponseDTO providerResponseDTO = this.providerServiceImpl.createProvider(providerCreateDTO);
        URI location = URI.create("/api/v2/providers/" + providerResponseDTO.getId());
        return ResponseEntity.created(location).body(providerResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a provider by ID",
            description = "Deletes a provider using their unique ID. Requires the ID to exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Provider not found")
    })
    public ResponseEntity<MessageResponseDTO> deleteProvider(
            @Parameter(description = "ID of the provider to delete", example = "1")
            @PathVariable Long id) {
        this.providerServiceImpl.deleteProviderById(id);
        return ResponseEntity.ok(new MessageResponseDTO("El proveedor fué eliminado exitosamente."));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get provider by ID",
            description = "Retrieves the provider's information using their unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider found successfully"),
            @ApiResponse(responseCode = "404", description = "Provider not found")
    })
    public ResponseEntity<ProviderResponseDTO> getProvider(
            @Parameter(description = "ID of the provider to retrieve", example = "1")
            @PathVariable Long id) {
        ProviderResponseDTO providerResponseDTO = this.providerServiceImpl.getProviderById(id);
        return ResponseEntity.ok(providerResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update provider by ID",
            description = "Updates the provider's information using their unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider updated successfully"),
            @ApiResponse(responseCode = "404", description = "Provider not found")
    })
    public ResponseEntity<ProviderResponseDTO> updateProvider(
            @Parameter(description = "ID of the provider to update", example = "1")
            @PathVariable Long id, @Valid @RequestBody ProviderUpdateDTO providerUpdateDTO) {
        ProviderResponseDTO providerResponseDTO = this.providerServiceImpl.updateProvider(id, providerUpdateDTO);
        return ResponseEntity.ok(providerResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all providers",
            description = "Retrieves a list of all providers registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of providers retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "There're not providers at system.")
    })
    public ResponseEntity<List<ProviderResponseDTO>> getProviders() {
        List<ProviderResponseDTO> providersResponseDTOS = this.providerServiceImpl.getProviders();
        return ResponseEntity.ok(providersResponseDTOS);
    }
}