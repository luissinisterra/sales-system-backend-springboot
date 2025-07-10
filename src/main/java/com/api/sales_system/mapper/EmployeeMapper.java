package com.api.sales_system.mapper;

import com.api.sales_system.dto.EmployeeCreateDTO;
import com.api.sales_system.dto.EmployeeResponseDTO;
import com.api.sales_system.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateDTO employeeRequestDTO);
    EmployeeResponseDTO toResponseDTO(Employee employee);
    List<EmployeeResponseDTO> toResponseList(List<Employee> employees);
}
