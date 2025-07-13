package com.api.sales_system.mapper;

import com.api.sales_system.dto.ProviderCreateDTO;
import com.api.sales_system.dto.ProviderResponseDTO;
import com.api.sales_system.entity.Provider;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    Provider toEntity(ProviderCreateDTO providerCreateDTO);

    ProviderResponseDTO toResponseDTO(Provider provider);

    List<ProviderResponseDTO> toResponseList(List<Provider> providers);

}