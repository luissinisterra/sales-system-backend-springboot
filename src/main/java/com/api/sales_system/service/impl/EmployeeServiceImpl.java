package com.api.sales_system.service.impl;

import com.api.sales_system.dto.EmployeeCreateDTO;
import com.api.sales_system.dto.EmployeeResponseDTO;
import com.api.sales_system.entity.Employee;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.EmployeeMapper;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = this.employeeMapper.toEntity(employeeCreateDTO);
        String encodedPassword = this.passwordEncoder.encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        return this.employeeMapper.toResponseDTO(this.employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        this.employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        return this.employeeMapper.toResponseDTO(employeeOpt.get());
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();

        if (employees.isEmpty()) throw new ResourceNotFoundException("Aun no hay empleados registrados en el sistema.");

        return this.employeeMapper.toResponseList(employees);
    }
}
