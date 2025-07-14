package com.api.sales_system.controller;

import com.api.sales_system.dto.MessageResponseDTO;
import com.api.sales_system.dto.RoleCreateDTO;
import com.api.sales_system.dto.RoleResponseDTO;
import com.api.sales_system.dto.RoleUpdateDTO;
import com.api.sales_system.service.RoleService;
import com.api.sales_system.service.impl.RoleServiceImpl;
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
@RequestMapping("api/v2/roles")
@Tag(name = "Roles", description = "Endpoints for managing roles in the Sales System")
public class RoleController {

    private final RoleService roleServiceImpl;

    @Autowired
    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new role",
            description = "Registers a new role with a unique name. Validates input data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data"),
            @ApiResponse(responseCode = "409", description = "Conflict - role ID already exists")
    })
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleCreateDTO roleRequestDTO) {
        RoleResponseDTO roleResponseDTO = this.roleServiceImpl.createRole(roleRequestDTO);
        URI location = URI.create("/api/v2/roles/" + roleResponseDTO.getId());
        return ResponseEntity.created(location).body(roleResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a role by ID",
            description = "Deletes a role using its unique ID. Requires the ID to exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<MessageResponseDTO> deleteRole(
            @Parameter(description = "ID of the role to delete", example = "1")
            @PathVariable Long id) {
        this.roleServiceImpl.deleteRoleById(id);
        return ResponseEntity.ok(new MessageResponseDTO("El rol fué eliminado exitosamente."));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get role by ID",
            description = "Retrieves the role’s information using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<RoleResponseDTO> getRole(
            @Parameter(description = "ID of the role to retrieve", example = "1")
            @PathVariable Long id) {
        RoleResponseDTO roleResponseDTO = this.roleServiceImpl.getRoleById(id);
        return ResponseEntity.ok(roleResponseDTO);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update role by ID",
            description = "Updates the role's name using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<RoleResponseDTO> updateRole(
            @Parameter(description = "ID of the role to update", example = "1")
            @PathVariable Long id, @Valid @RequestBody RoleUpdateDTO roleUpdateDTO){
        RoleResponseDTO roleResponseDTO = this.roleServiceImpl.updateRole(id, roleUpdateDTO);
        return ResponseEntity.ok(roleResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all roles",
            description = "Retrieves a list of all roles registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of roles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "There're not roles at system.")
    })
    public ResponseEntity<List<RoleResponseDTO>> getRoles() {
        List<RoleResponseDTO> rolesResponseDTOS = this.roleServiceImpl.getRoles();
        return ResponseEntity.ok(rolesResponseDTOS);
    }
}
