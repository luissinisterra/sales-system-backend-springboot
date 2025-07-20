package com.api.sales_system.service.impl;

import com.api.sales_system.dto.*;
import com.api.sales_system.entity.*;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.EmployeeMapper;
import com.api.sales_system.mapper.ProductMapper;
import com.api.sales_system.mapper.ProviderMapper;
import com.api.sales_system.mapper.PurchaseMapper;
import com.api.sales_system.repository.EmployeeRepository;
import com.api.sales_system.repository.ProductRepository;
import com.api.sales_system.repository.ProviderRepository;
import com.api.sales_system.repository.PurchaseRepository;
import com.api.sales_system.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProviderRepository providerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final PurchaseMapper purchaseMapper;
    private final ProviderMapper providerMapper;
    private final EmployeeMapper employeeMapper;
    private final ProductMapper productMapper;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            ProviderRepository providerRepository,
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            PurchaseMapper purchaseMapper,
            ProviderMapper providerMapper,
            EmployeeMapper employeeMapper,
            ProductMapper productMapper
    ) {
        this.purchaseRepository = purchaseRepository;
        this.providerRepository = providerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.purchaseMapper = purchaseMapper;
        this.providerMapper = providerMapper;
        this.employeeMapper = employeeMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public PurchaseResponseDTO createPurchase(PurchaseCreateDTO purchaseCreateDTO) {
        Provider provider = providerRepository.findById(purchaseCreateDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));

        Employee employee = employeeRepository.findById(purchaseCreateDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        Purchase purchase = purchaseMapper.toEntity(purchaseCreateDTO);
        purchase.setProvider(provider);
        purchase.setEmployee(employee);

        for (int i = 0; i < purchase.getPurchaseDetails().size(); i++) {
            PurchaseDetail detail = purchase.getPurchaseDetails().get(i);
            Long productId = purchaseCreateDTO.getPurchaseDetails().get(i).getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + productId + " no existe."));

            detail.setProduct(product);
            detail.setPurchase(purchase);
        }

        PurchaseResponseDTO purchaseResponseDTO = this.purchaseMapper.toResponseDTO(this.purchaseRepository.save(purchase));

        ProviderResponseDTO providerResponseDTO = this.providerMapper.toResponseDTO(provider);
        purchaseResponseDTO.setProvider(providerResponseDTO);

        EmployeeResponseDTO employeeResponseDTO =  this.employeeMapper.toResponseDTO(employee);
        purchaseResponseDTO.setEmployee(employeeResponseDTO);

        for (int i = 0; i < purchaseResponseDTO.getPurchaseDetails().size(); i++) {
            PurchaseDetailResponseDTO detail = purchaseResponseDTO.getPurchaseDetails().get(i);
            Long productId = purchaseCreateDTO.getPurchaseDetails().get(i).getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + productId + " no existe."));

            ProductResponseDTO productResponseDTO = this.productMapper.toResponseDTO(product);

            detail.setProduct(productResponseDTO);
        }

        return purchaseMapper.toResponseDTO(purchaseRepository.save(purchase));
    }


    @Override
    @Transactional
    public void deletePurchaseById(Long id) {
        Purchase purchase = this.purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La compra con ID " + id + " no fue encontrada."));

        this.purchaseRepository.delete(purchase);
    }

    @Override
    public PurchaseResponseDTO getPurchaseById(Long id) {
        Purchase purchase = this.purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La compra con ID " + id + " no fue encontrada."));

        return this.purchaseMapper.toResponseDTO(purchase);
    }

    /*@Override
    @Transactional
    public PurchaseResponseDTO updatePurchase(Long id, PurchaseUpdateDTO purchaseUpdateDTO) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La compra con ID " + id + " no fue encontrada."));

        Provider provider = providerRepository.findById(purchaseUpdateDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor con ID " + purchaseUpdateDTO.getProviderId() + " no encontrado."));

        Employee employee = employeeRepository.findById(purchaseUpdateDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + purchaseUpdateDTO.getEmployeeId() + " no encontrado."));

        purchase.setProvider(provider);
        purchase.setEmployee(employee);
        purchase.setPurchaseDate(purchaseUpdateDTO.getPurchaseDate());
        purchase.setTotalAmount(purchaseUpdateDTO.getTotalAmount());

        // Si también deseas actualizar los detalles (purchaseDetails), deberías manejarlos aquí.

        return purchaseMapper.toResponseDTO(purchaseRepository.save(purchase));
    }*/

    @Override
    public List<PurchaseResponseDTO> getPurchases() {
        List<Purchase> purchases = this.purchaseRepository.findAll();

        if (purchases.isEmpty()) return List.of();

        return this.purchaseMapper.toResponseList(purchases);
    }
}
