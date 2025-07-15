package com.api.sales_system.controller;

import com.api.sales_system.dto.*;
import com.api.sales_system.service.EmployeeService;
import com.api.sales_system.service.impl.EmployeeServiceImpl;
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
@RequestMapping("api/v2/employees")
@Tag(name = "Employees", description = "Endpoints for managing employees in the Sales System")
public class EmployeeController {

    private final EmployeeService employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @PostMapping
    @Operation(
            summary = "Create a new employee.",
            description = "Registers a new employee with unique username. Validates input data such as name, role, password."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully."),
            @ApiResponse(responseCode = "400", description = "Validation error - invalid input data."),
            @ApiResponse(responseCode = "409", description = "Conflict - username already exists.")
    })
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        EmployeeResponseDTO employeeResponseDTO = this.employeeServiceImpl.createEmployee(employeeCreateDTO);
        URI location = URI.create("/api/v2/employees/" + employeeResponseDTO.getId());
        return ResponseEntity.created(location).body(employeeResponseDTO);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a employee by ID.",
            description = "Deletes a employee using their ID. Requires the ID to exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<MessageResponseDTO> deleteEmployee(
            @Parameter(description = "ID of the employee to delete.", example = "1")
            @PathVariable Long id) {
        this.employeeServiceImpl.deleteEmployeeById(id);
        return ResponseEntity.ok(new MessageResponseDTO("El empleado fué eliminado exitosamente."));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get employee by ID.",
            description = "Retrieves the employee’s information using their unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<EmployeeResponseDTO> getEmployee(
            @Parameter(description = "ID of the employee to retrieve.", example = "1")
            @PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = this.employeeServiceImpl.getEmployeeById(id);
        return ResponseEntity.ok(employeeResponseDTO);

    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update employee by ID.",
            description = "Updates the employee's information using their ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @Parameter(description = "ID of the employee to retrieve.", example = "1")
            @PathVariable Long id, @Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        EmployeeResponseDTO employeeResponseDTO = this.employeeServiceImpl.updateEmployee(id, employeeUpdateDTO);
        return ResponseEntity.ok(employeeResponseDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all employees.",
            description = "Retrieves a list of all employees registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "There're not employees at system.")
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        List<EmployeeResponseDTO> employeeResponseDTOS = this.employeeServiceImpl.getEmployees();
        return ResponseEntity.ok(employeeResponseDTOS);
    }

    @PutMapping("{id}/profile")
    @Operation(
            summary = "Update the employee profile.",
            description = "Update the employee's profiles using their ID."
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Employee profile updated successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<MessageResponseDTO> updateOwnProfile(
            @Parameter(description = "ID of the employee to retrieve.", example = "1")
            @PathVariable Long id, @Valid @RequestBody UpdateProfileRequestDTO updateProfileRequestDTO) {
        this.employeeServiceImpl.updateOwnProfile(id, updateProfileRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("Perfil actualizado correctamente."));
    }

    @PutMapping("{id}/change-password")
    @Operation(
            summary = "Change employee password.",
            description = "Change the employee's password using their ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee password changed successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<MessageResponseDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        this.employeeServiceImpl.changePassword(id, changePasswordRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("La contraseña ha sido actualizada exitosamente."));
    }

    @PutMapping("{id}/reset-password")
    @Operation(
            summary = "Reset employee password.",
            description = "Reset the employee's password using their ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee password reseted successfully."),
            @ApiResponse(responseCode = "404", description = "Employee not found.")
    })
    public ResponseEntity<MessageResponseDTO> resetPassword(@PathVariable Long id, @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){
        this.employeeServiceImpl.resetPassword(id, resetPasswordRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("La contraseña ha sido actualizada exitosamente."));
    }

}
