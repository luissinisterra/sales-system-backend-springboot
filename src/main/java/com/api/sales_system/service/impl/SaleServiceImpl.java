package com.api.sales_system.service.impl;

import com.api.sales_system.dto.*;
import com.api.sales_system.entity.*;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.ClientMapper;
import com.api.sales_system.mapper.EmployeeMapper;
import com.api.sales_system.mapper.ProductMapper;
import com.api.sales_system.mapper.SaleMapper;
import com.api.sales_system.repository.ClientRepository;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;
    private final ClientMapper clientMapper;
    private final EmployeeMapper employeeMapper;
    private final ProductMapper productMapper;

    @Autowired
    public SaleServiceImpl(
            SaleRepository saleRepository,
            ClientRepository clientRepository,
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            SaleMapper saleMapper,
            ClientMapper clientMapper,
            ProductMapper productMapper,
            EmployeeMapper employeeMapper
    ) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.saleMapper = saleMapper;
        this.clientMapper = clientMapper;
        this.employeeMapper = employeeMapper;
        this.productMapper = productMapper;
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

        for (int i = 0; i < sale.getSaleDetails().size(); i++) {
            SaleDetail detail = sale.getSaleDetails().get(i);
            Long productId = saleCreateDTO.getSaleDetails().get(i).getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + productId + " no existe."));

            detail.setProduct(product);
            detail.setSale(sale);
        }

        SaleResponseDTO saleResponseDTO = this.saleMapper.toResponseDTO(this.saleRepository.save(sale));
        ClientResponseDTO clientResponseDTO = this.clientMapper.toResponseDTO(client);
        EmployeeResponseDTO employeeResponseDTO = this.employeeMapper.toResponseDTO(employee);

        saleResponseDTO.setClient(clientResponseDTO);
        saleResponseDTO.setEmployee(employeeResponseDTO);

        for (int i = 0; i < saleResponseDTO.getSaleDetails().size(); i++) {
            SaleDetailResponseDTO detail = saleResponseDTO.getSaleDetails().get(i);
            Long productId = saleCreateDTO.getSaleDetails().get(i).getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + productId + " no existe."));

            ProductResponseDTO productResponseDTO = this.productMapper.toResponseDTO(product);

            detail.setProduct(productResponseDTO);
        }

        return saleResponseDTO;
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

    @Override
    public List<SaleResponseDTO> getSales() {
        List<Sale> sales = this.saleRepository.findAll();

        if (sales.isEmpty()) return List.of();

        return this.saleMapper.toResponseList(sales);
    }
}
