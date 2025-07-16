package com.api.sales_system.service.impl;

import com.api.sales_system.dto.SaleCreateDTO;
import com.api.sales_system.dto.SaleResponseDTO;
import com.api.sales_system.entity.Client;
import com.api.sales_system.entity.Employee;
import com.api.sales_system.entity.Sale;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.SaleMapper;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.repository.SaleRepository;
import com.api.sales_system.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final SaleMapper saleMapper;

    @Autowired
    public SaleServiceImpl(
            SaleRepository saleRepository,
            ClientRepository clientRepository,
            EmployeeRepository employeeRepository,
            SaleMapper saleMapper
    ) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    @Transactional
    public SaleResponseDTO createSale(SaleCreateDTO saleCreateDTO) {
        Client client = this.clientRepository.findById(saleCreateDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + saleCreateDTO.getClientId() + " no encontrado."));

        Employee employee = this.employeeRepository.findById(saleCreateDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + saleCreateDTO.getEmployeeId() + " no encontrado."));

        Sale sale = this.saleMapper.toEntity(saleCreateDTO);

        sale.setClient(client);
        sale.setEmployee(employee);

        return this.saleMapper.toResponseDTO(this.saleRepository.save(sale));
    }

    @Override
    @Transactional
    public void deleteSaleById(Long id) {
        Sale sale = this.saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La venta con ID " + id + " no fue encontrada."));

        this.saleRepository.delete(sale);
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = this.saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La venta con ID " + id + " no fue encontrada."));

        return this.saleMapper.toResponseDTO(sale);
    }

    /*@Override
    @Transactional
    public SaleResponseDTO updateSale(Long id, SaleUpdateDTO saleUpdateDTO) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La venta con ID " + id + " no fue encontrada."));

        Client client = clientRepository.findById(saleUpdateDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + saleUpdateDTO.getClientId() + " no encontrado."));

        Employee employee = employeeRepository.findById(saleUpdateDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + saleUpdateDTO.getEmployeeId() + " no encontrado."));

        sale.setClient(client);
        sale.setEmployee(employee);
        sale.setSaleDate(saleUpdateDTO.getSaleDate());
        sale.setTotalAmount(saleUpdateDTO.getTotalAmount());

        // Si deseas actualizar los detalles de la venta (saleDetails), deberías manejarlos aquí.

        return saleMapper.toResponseDTO(saleRepository.save(sale));
    }*/

    @Override
    public List<SaleResponseDTO> getSales() {
        List<Sale> sales = this.saleRepository.findAll();

        if (sales.isEmpty()) return List.of();

        return this.saleMapper.toResponseList(sales);
    }
}
