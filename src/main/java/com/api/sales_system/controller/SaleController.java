package com.api.sales_system.controller;

import com.api.sales_system.dto.*;
import com.api.sales_system.service.SaleService;
import com.api.sales_system.service.impl.SaleServiceImpl;
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
@RequestMapping("api/v2/sales")
@Tag(name = "Sales", description = "Endpoints for managing sales in the Sales System")
public class SaleController {

    private final SaleService saleServiceImpl;

    @Autowired
    public SaleController(SaleServiceImpl saleServiceImpl) {
        this.saleServiceImpl = saleServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new sale",
            description = "Registers a new sale with associated client, employee, total amount, and sale details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data")
    })
    public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleCreateDTO saleCreateDTO) {
        SaleResponseDTO saleResponseDTO = this.saleServiceImpl.createSale(saleCreateDTO);
        URI location = URI.create("/api/v2/sales/" + saleResponseDTO.getId());
        return ResponseEntity.created(location).body(saleResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a sale by ID",
            description = "Deletes a sale by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<MessageResponseDTO> deleteSale(
            @Parameter(description = "ID of the sale to delete", example = "1")
            @PathVariable Long id) {
        this.saleServiceImpl.deleteSaleById(id);
        return ResponseEntity.ok(new MessageResponseDTO("La venta fue eliminada exitosamente."));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get sale by ID",
            description = "Retrieves sale information using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<SaleResponseDTO> getSale(
            @Parameter(description = "ID of the sale to retrieve", example = "1")
            @PathVariable Long id) {
        SaleResponseDTO saleResponseDTO = this.saleServiceImpl.getSaleById(id);
        return ResponseEntity.ok(saleResponseDTO);
    }

    /*@PutMapping("/{id}")
    @Operation(
            summary = "Update sale by ID",
            description = "Updates a sale using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    public ResponseEntity<SaleResponseDTO> updateSale(
            @Parameter(description = "ID of the sale to update", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody SaleUpdateDTO saleUpdateDTO) {
        SaleResponseDTO saleResponseDTO = this.saleServiceImpl.updateSale(id, saleUpdateDTO);
        return ResponseEntity.ok(saleResponseDTO);
    }*/

    @GetMapping
    @Operation(
            summary = "Get all sales",
            description = "Retrieves a list of all sales in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of sales retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No sales found")
    })
    public ResponseEntity<List<SaleResponseDTO>> getSales() {
        List<SaleResponseDTO> saleList = this.saleServiceImpl.getSales();
        return ResponseEntity.ok(saleList);
    }
}
