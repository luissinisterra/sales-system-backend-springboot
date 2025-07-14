package com.api.sales_system.mapper;

import com.api.sales_system.dto.RoleCreateDTO;
import com.api.sales_system.dto.RoleResponseDTO;
import com.api.sales_system.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleCreateDTO roleCreateDTO);

    RoleResponseDTO toResponseDTO(Role role);

    List<RoleResponseDTO> toResponseList(List<Role> roles);
}
