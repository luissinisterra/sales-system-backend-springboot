package com.api.sales_system.mapper;

import com.api.sales_system.dto.ClientRequestDTO;
import com.api.sales_system.dto.ClientResponseDTO;
import com.api.sales_system.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO toResponseDTO(Client client);
    List<ClientResponseDTO> toResponseList(List<Client> clients);
}
