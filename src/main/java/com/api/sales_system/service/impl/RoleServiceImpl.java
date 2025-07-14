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
        Role role = this.roleMapper.toEntity(roleCreateDTO);

        return this.roleMapper.toResponseDTO(this.roleRepository.save(role));
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        Optional<Role> roleOpt = this.roleRepository.findById(id);

        if(roleOpt.isEmpty()) throw new ResourceNotFoundException("El rol con el id: " + id + " no fué encontrado.");

        this.roleRepository.deleteById(id);
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Optional<Role> roleOpt = this.roleRepository.findById(id);

        if (roleOpt.isEmpty()) throw new ResourceNotFoundException("El rol con el id: " + id + " no fué encontrado.");

        return this.roleMapper.toResponseDTO(roleOpt.get());
    }

    @Override
    @Transactional
    public RoleResponseDTO updateRole(Long id, RoleUpdateDTO roleUpdateDTO){
        Optional<Role> roleOpt = this.roleRepository.findById(id);

        if (roleOpt.isEmpty()) throw new ResourceNotFoundException("El rol con el id: " + id + " no fué encontrado.");

        roleOpt.get().setName(roleUpdateDTO.getName());

        return this.roleMapper.toResponseDTO(this.roleRepository.save(roleOpt.get()));
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        List<Role> roles = this.roleRepository.findAll();

        if (roles.isEmpty()) return List.of();

        return this.roleMapper.toResponseList(roles);
    }
}
