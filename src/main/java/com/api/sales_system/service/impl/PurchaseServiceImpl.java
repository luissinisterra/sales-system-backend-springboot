package com.api.sales_system.service.impl;

import com.api.sales_system.dto.PurchaseCreateDTO;
import com.api.sales_system.dto.PurchaseResponseDTO;
import com.api.sales_system.entity.Employee;
import com.api.sales_system.entity.Provider;
import com.api.sales_system.entity.Purchase;
import com.api.sales_system.exception.ResourceNotFoundException;
import com.api.sales_system.mapper.PurchaseMapper;
import com.api.sales_system.repository.EmployeeRepository;
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
    private final PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            ProviderRepository providerRepository,
            EmployeeRepository employeeRepository,
            PurchaseMapper purchaseMapper
    ) {
        this.purchaseRepository = purchaseRepository;
        this.providerRepository = providerRepository;
        this.employeeRepository = employeeRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    @Transactional
    public PurchaseResponseDTO createPurchase(PurchaseCreateDTO purchaseCreateDTO) {
        Provider provider = this.providerRepository.findById(purchaseCreateDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor con ID " + purchaseCreateDTO.getProviderId() + " no encontrado."));

        Employee employee = this.employeeRepository.findById(purchaseCreateDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + purchaseCreateDTO.getEmployeeId() + " no encontrado."));

        Purchase purchase = this.purchaseMapper.toEntity(purchaseCreateDTO);
        purchase.setProvider(provider);
        purchase.setEmployee(employee);

        return this.purchaseMapper.toResponseDTO(this.purchaseRepository.save(purchase));
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
