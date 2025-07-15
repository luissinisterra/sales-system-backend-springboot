package com.api.sales_system.service.impl;

import com.api.sales_system.dto.*;
import com.api.sales_system.entity.Employee;
import com.api.sales_system.entity.Role;
import com.api.sales_system.exception.CurrentPasswordInvalidException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.EmployeeMapper;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.repository.RoleRepository;
import com.api.sales_system.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        Role role = this.roleRepository.findById(employeeCreateDTO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no existente."));

        Employee employee = this.employeeMapper.toEntity(employeeCreateDTO);

        employee.setRole(role);

        String encodedPassword = this.passwordEncoder.encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        return this.employeeMapper.toResponseDTO(this.employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        this.employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        return this.employeeMapper.toResponseDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        Role role = this.roleRepository.findById(employeeUpdateDTO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no existente."));

        employee.setFirstName(employeeUpdateDTO.getFirstName());
        employee.setLastName(employeeUpdateDTO.getLastName());
        employee.setRole(role);
        employee.setUserName(employeeUpdateDTO.getUserName());

        return this.employeeMapper.toResponseDTO(this.employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();

        if (employees.isEmpty()) return List.of();

        return this.employeeMapper.toResponseList(employees);
    }

    @Override
    @Transactional
    public void updateOwnProfile(Long id, UpdateProfileRequestDTO updateProfileRequestDTO) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        employee.setFirstName(updateProfileRequestDTO.getFirstName());
        employee.setLastName(updateProfileRequestDTO.getLastName());
        employee.setUserName(updateProfileRequestDTO.getUserName());

        this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDTO changePasswordRequestDTO) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        boolean match = this.passwordEncoder.matches(changePasswordRequestDTO.getCurrentPassword(), employee.getPassword());

        if (!match) throw new CurrentPasswordInvalidException("Contraseña actual incorrecta.");

        String newEncodedPassword = this.passwordEncoder.encode(changePasswordRequestDTO.getNewPassword());

        employee.setPassword(newEncodedPassword);

        this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void resetPassword(Long id, ResetPasswordRequestDTO resetPasswordRequestDTO) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        String encodedPassword = this.passwordEncoder.encode(resetPasswordRequestDTO.getNewPassword());

        employee.setPassword(encodedPassword);

        this.employeeRepository.save(employee);
    }
}
