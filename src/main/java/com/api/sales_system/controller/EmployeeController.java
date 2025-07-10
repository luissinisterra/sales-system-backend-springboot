package com.api.sales_system.controller;

import com.api.sales_system.dto.EmployeeCreateDTO;
import com.api.sales_system.dto.EmployeeResponseDTO;
import com.api.sales_system.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeRequestDTO){
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.createEmployee(employeeRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        this.employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id){
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        List<EmployeeResponseDTO> employeeResponseDTOS = this.employeeService.getEmployees();
        return new ResponseEntity<>(employeeResponseDTOS, HttpStatus.OK);
    }
}
