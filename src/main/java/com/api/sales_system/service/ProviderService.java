package com.api.sales_system.service;

import com.api.sales_system.dto.ProviderCreateDTO;
import com.api.sales_system.dto.ProviderResponseDTO;
import com.api.sales_system.dto.ProviderUpdateDTO;

import java.util.List;

public interface ProviderService {
    ProviderResponseDTO createProvider(ProviderCreateDTO providerCreateDTO);
    void deleteProviderById(Long id);
    ProviderResponseDTO getProviderById(Long id);
    ProviderResponseDTO updateProvider(Long id, ProviderUpdateDTO providerUpdateDTO);
    List<ProviderResponseDTO> getProviders();
}
