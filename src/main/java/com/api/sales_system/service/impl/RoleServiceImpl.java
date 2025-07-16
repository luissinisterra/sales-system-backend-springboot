package com.api.sales_system.service.impl;

import com.api.sales_system.dto.RoleCreateDTO;
import com.api.sales_system.dto.RoleResponseDTO;
import com.api.sales_system.dto.RoleUpdateDTO;
import com.api.sales_system.entity.Role;
import com.api.sales_system.exception.ResourceAlreadyExistsException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.RoleMapper;
import com.api.sales_system.repository.RoleRepository;
import com.api.sales_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public RoleResponseDTO createRole(RoleCreateDTO roleCreateDTO) {
        boolean exists = this.roleRepository.existsByName(roleCreateDTO.getName());
        if (exists) {
            throw new ResourceNotFoundException("Role con nombre " + roleCreateDTO.getName() + " ya existe en el sistema.");
        }

        Role role = this.roleMapper.toEntity(roleCreateDTO);
        return this.roleMapper.toResponseDTO(this.roleRepository.save(role));
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        this.roleRepository.delete(role);
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        return this.roleMapper.toResponseDTO(role);
    }

    @Override
    @Transactional
    public RoleResponseDTO updateRole(Long id, RoleUpdateDTO roleUpdateDTO){
        Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        role.setName(roleUpdateDTO.getName());

        return this.roleMapper.toResponseDTO(this.roleRepository.save(role));
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        List<Role> roles = this.roleRepository.findAll();

        if (roles.isEmpty()) return List.of();

        return this.roleMapper.toResponseList(roles);
    }
}
