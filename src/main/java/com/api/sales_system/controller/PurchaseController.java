package com.api.sales_system.controller;

import com.api.sales_system.dto.*;
import com.api.sales_system.service.PurchaseService;
import com.api.sales_system.service.impl.PurchaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/purchases")
@Tag(name = "Purchases", description = "Endpoints for managing purchases in the Sales System")
public class PurchaseController {

    private final PurchaseService purchaseServiceImpl;

    @Autowired
    public PurchaseController(PurchaseServiceImpl purchaseServiceImpl) {
        this.purchaseServiceImpl = purchaseServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new purchase",
            description = "Registers a new purchase with associated provider, employee, total amount, and purchase details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data")
    })
    public ResponseEntity<PurchaseResponseDTO> createPurchase(@Valid @RequestBody PurchaseCreateDTO purchaseCreateDTO) {
        PurchaseResponseDTO purchaseResponseDTO = this.purchaseServiceImpl.createPurchase(purchaseCreateDTO);
        URI location = URI.create("/api/v2/purchases/" + purchaseResponseDTO.getId());
        return ResponseEntity.created(location).body(purchaseResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a purchase by ID",
            description = "Deletes a purchase by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")
    })
    public ResponseEntity<MessageResponseDTO> deletePurchase(
            @Parameter(description = "ID of the purchase to delete", example = "1")
            @PathVariable Long id) {
        this.purchaseServiceImpl.deletePurchaseById(id);
        return ResponseEntity.ok(new MessageResponseDTO("La compra fue eliminada exitosamente."));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get purchase by ID",
            description = "Retrieves purchase information using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")
    })
    public ResponseEntity<PurchaseResponseDTO> getPurchase(
            @Parameter(description = "ID of the purchase to retrieve", example = "1")
            @PathVariable Long id) {
        PurchaseResponseDTO purchaseResponseDTO = this.purchaseServiceImpl.getPurchaseById(id);
        return ResponseEntity.ok(purchaseResponseDTO);
    }

    /*@PutMapping("/{id}")
    @Operation(
            summary = "Update purchase by ID",
            description = "Updates a purchase using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase updated successfully"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")
    })
    public ResponseEntity<PurchaseResponseDTO> updatePurchase(
            @Parameter(description = "ID of the purchase to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody PurchaseUpdateDTO purchaseUpdateDTO) {
        PurchaseResponseDTO purchaseResponseDTO = this.purchaseServiceImpl.updatePurchase(id, purchaseUpdateDTO);
        return ResponseEntity.ok(purchaseResponseDTO);
    }*/

    @GetMapping
    @Operation(
            summary = "Get all purchases",
            description = "Retrieves a list of all purchases in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of purchases retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No purchases found")
    })
    public ResponseEntity<List<PurchaseResponseDTO>> getPurchases() {
        List<PurchaseResponseDTO> purchaseList = this.purchaseServiceImpl.getPurchases();
        return ResponseEntity.ok(purchaseList);
    }
}
