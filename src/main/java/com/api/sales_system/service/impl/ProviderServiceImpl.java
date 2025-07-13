package com.api.sales_system.service.impl;

import com.api.sales_system.dto.ProviderCreateDTO;
import com.api.sales_system.dto.ProviderResponseDTO;
import com.api.sales_system.dto.ProviderUpdateDTO;
import com.api.sales_system.entity.Provider;
import com.api.sales_system.exception.ResourceAlreadyExistsException;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.ProviderMapper;
import com.api.sales_system.repository.ProviderRepository;
import com.api.sales_system.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    @Override
    @Transactional
    public ProviderResponseDTO createProvider(ProviderCreateDTO providerCreateDTO) {
        Optional<Provider> providerOpt = this.providerRepository.findById(providerCreateDTO.getId());

        if (providerOpt.isPresent())
            throw new ResourceAlreadyExistsException("El proveedor con el id: " + providerOpt.get().getId() + " ya exíste en el sistéma.");

        Provider provider = this.providerMapper.toEntity(providerCreateDTO);

        return this.providerMapper.toResponseDTO(this.providerRepository.save(provider));
    }

    @Override
    @Transactional
    public void deleteProviderById(Long id) {
        Optional<Provider> providerOpt = this.providerRepository.findById(id);

        if (providerOpt.isEmpty())
            throw new ResourceNotFoundException("El proveedor con el id: " + id + " no fué encontrado.");

        this.providerRepository.deleteById(id);
    }

    @Override
    public ProviderResponseDTO getProviderById(Long id) {
        Optional<Provider> providerOpt = this.providerRepository.findById(id);

        if (providerOpt.isEmpty())
            throw new ResourceNotFoundException("El proveedor con el id: " + id + " no fué encontrado.");

        return this.providerMapper.toResponseDTO(providerOpt.get());
    }

    @Override
    @Transactional
    public ProviderResponseDTO updateProvider(Long id, ProviderUpdateDTO providerUpdateDTO) {
        Optional<Provider> providerOpt = this.providerRepository.findById(id);

        if (providerOpt.isEmpty())
            throw new ResourceNotFoundException("El proveedor con el id: " + id + " no fué encontrado.");

        Provider provider = providerOpt.get();

        // Actualizar campos del proveedor
        provider.setCompanyName(providerUpdateDTO.getCompanyName());
        provider.setContactPerson(providerUpdateDTO.getContactPerson());
        provider.setPhoneNumber(providerUpdateDTO.getPhoneNumber());
        provider.setEmail(providerUpdateDTO.getEmail());
        provider.setAddress(providerUpdateDTO.getAddress());
        provider.setCity(providerUpdateDTO.getCity());
        provider.setCountry(providerUpdateDTO.getCountry());

        return this.providerMapper.toResponseDTO(this.providerRepository.save(provider));
    }

    @Override
    public List<ProviderResponseDTO> getProviders() {
        List<Provider> providers = this.providerRepository.findAll();

        if (providers.isEmpty()) return List.of();

        return this.providerMapper.toResponseList(providers);
    }
}