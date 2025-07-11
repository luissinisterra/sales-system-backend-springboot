package com.api.sales_system.service.impl;

import com.api.sales_system.dto.*;
import com.api.sales_system.entity.Employee;
import com.api.sales_system.exception.CurrentPasswordInvalidException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.EmployeeMapper;
import com.api.sales_system.repository.EmployeeRepository;
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
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = this.employeeMapper.toEntity(employeeCreateDTO);
        String encodedPassword = this.passwordEncoder.encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        return this.employeeMapper.toResponseDTO(this.employeeRepository.save(employee));
    }

    @Override
    @Transactional
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
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        employeeOpt.get().setFirstName(employeeUpdateDTO.getFirstName());
        employeeOpt.get().setLastName(employeeUpdateDTO.getLastName());
        employeeOpt.get().setRole(employeeUpdateDTO.getRole());
        employeeOpt.get().setUserName(employeeUpdateDTO.getUserName());

        return this.employeeMapper.toResponseDTO(this.employeeRepository.save(employeeOpt.get()));
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
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        employeeOpt.get().setFirstName(updateProfileRequestDTO.getFirstName());
        employeeOpt.get().setLastName(updateProfileRequestDTO.getLastName());
        employeeOpt.get().setUserName(updateProfileRequestDTO.getUserName());

        this.employeeRepository.save(employeeOpt.get());
    }

    @Override
    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDTO changePasswordRequestDTO) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        boolean match = this.passwordEncoder.matches(changePasswordRequestDTO.getCurrentPassword(), employeeOpt.get().getPassword());

        if (!match) throw new CurrentPasswordInvalidException("Contraseña actual incorrecta.");

        String newEncodedPassword = this.passwordEncoder.encode(changePasswordRequestDTO.getNewPassword());

        employeeOpt.get().setPassword(newEncodedPassword);

        this.employeeRepository.save(employeeOpt.get());
    }

    @Override
    @Transactional
    public void resetPassword(Long id, ResetPasswordRequestDTO resetPasswordRequestDTO) {
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isEmpty()) throw new ResourceNotFoundException("El empleado con el id: " + id + " no fué encontrado.");

        String encodedPassword = this.passwordEncoder.encode(resetPasswordRequestDTO.getNewPassword());

        employeeOpt.get().setPassword(encodedPassword);

        this.employeeRepository.save(employeeOpt.get());
    }
}
