package com.api.sales_system.controller;

import com.api.sales_system.dto.*;
import com.api.sales_system.service.EmployeeService;
import com.api.sales_system.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeRequestDTO){
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.createEmployee(employeeRequestDTO);
        URI location = URI.create("/api/v1/employees/" + employeeResponseDTO.getId());
        return ResponseEntity.created(location).body(employeeResponseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        this.employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id){
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeResponseDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO){
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.updateEmployee(id, employeeUpdateDTO);
        return ResponseEntity.ok(employeeResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        List<EmployeeResponseDTO> employeeResponseDTOS = this.employeeService.getEmployees();
        return ResponseEntity.ok(employeeResponseDTOS);
    }

    @PutMapping("{id}/profile")
    public ResponseEntity<MessageResponseDTO> updateOwnProfile(@PathVariable Long id, @Valid @RequestBody UpdateProfileRequestDTO updateProfileRequestDTO) {
        this.employeeService.updateOwnProfile(id, updateProfileRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("Perfil actualizado correctamente."));
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity<MessageResponseDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        this.employeeService.changePassword(id, changePasswordRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("La contraseña ha sido actualizada exitosamente."));
    }

    @PutMapping("{id}/reset-password")
    public ResponseEntity<MessageResponseDTO> resetPassoword(@PathVariable Long id, @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){
        this.employeeService.resetPassword(id, resetPasswordRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("La contraseña ha sido actualizada exitosamente."));
    }

}
