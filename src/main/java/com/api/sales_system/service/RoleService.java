package com.api.sales_system.service;

import com.api.sales_system.dto.RoleCreateDTO;
import com.api.sales_system.dto.RoleResponseDTO;
import com.api.sales_system.dto.RoleUpdateDTO;

import java.util.List;

public interface RoleService {
    RoleResponseDTO createRole(RoleCreateDTO roleCreateDTO);
    void deleteRoleById(Long id);
    RoleResponseDTO getRoleById(Long id);
    RoleResponseDTO updateRole(Long id, RoleUpdateDTO roleUpdateDTO);
    List<RoleResponseDTO> getRoles();
}
