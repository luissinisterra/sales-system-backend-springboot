package com.api.sales_system.service.impl;

import com.api.sales_system.dto.EmployeeRequestDTO;
import com.api.sales_system.dto.EmployeeResponseDTO;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        return null;
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return List.of();
    }
}
