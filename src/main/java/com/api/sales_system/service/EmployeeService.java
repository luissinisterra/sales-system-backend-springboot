package com.api.sales_system.service;

import com.api.sales_system.dto.EmployeeRequestDTO;
import com.api.sales_system.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployeeById(Long id);
    EmployeeResponseDTO getEmployeeById(Long id);
    List<EmployeeResponseDTO> getEmployees();
}
