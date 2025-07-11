package com.api.sales_system.service;

import com.api.sales_system.dto.*;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);
    void deleteEmployeeById(Long id);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO);
    List<EmployeeResponseDTO> getEmployees();
    void updateOwnProfile(Long id, UpdateProfileRequestDTO updateProfileRequestDTO);
    void changePassword(Long id, ChangePasswordRequestDTO changePasswordRequestDTO);
    void resetPassword(Long id, ResetPasswordRequestDTO resetPasswordRequestDTO);
}
